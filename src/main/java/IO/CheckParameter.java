package IO;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckParameter {
    
    final String               methodName     = "LoansFailureReason";
    StringBuilder              result         = new StringBuilder("");
    final String               newLine        = "\n";
    final Map<String, Integer> errorCodeMap   = new HashMap<>();
    final String               sourceCodeList = "f:/t/sourcecode.java";
    final String               myresultPath   = "f:/t/myresult.java";
    final String               erroeCodeList  = "f:/t/map.java";
    
    public static void main(String[] args) {
        CheckParameter t = new CheckParameter();
        try {
            t.readErrorCode();
            t.getMethod();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void getMethod() throws IOException {
        Files.lines(Paths.get(sourceCodeList))
                .map(line -> {
                    return line.substring(0,
                            line.lastIndexOf("java") + "java".length());
                }).forEach(line -> {
                    try {
                        ParseMethod(line.replaceAll("\\\\", "/"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
        writeToTxt();
    }
    
    public void writeToTxt() {
        try {
            Files.write(Paths.get(myresultPath), result.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void appendLog(String content) {
        result.append(content + newLine);
    }
    
    public void ParseMethod(String path) throws IOException {
        
        List<String> lineList = Files.readAllLines(Paths.get(path));
        int len = lineList.size();
        int tempLineNumber = 0;
        int lineNumberCount = 0;
        // System.out.println(path);
        for (int linenumber = 0; linenumber < len; linenumber++) {
            String value = lineList.get(linenumber).trim();
            StringBuilder b = new StringBuilder("");
            tempLineNumber = linenumber;
            if (value.contains(methodName)) {
                lineNumberCount = linenumber;
                // appendLog("----------------------------------------------------------------------------");
                // appendLog(path);
                for (int i = 0; i < value.length(); i++) {
                    b.append(value.charAt(i));
                    if (';' == value.charAt(i)) {
                        break;
                    }
                    
                    if (i + 1 == value.length()) {
                        ++tempLineNumber;
                        if (tempLineNumber < len) {
                            value = lineList.get(tempLineNumber).trim();
                            linenumber = tempLineNumber;
                            i = -1;
                        }
                    }
                }
                getIndex2(b.toString(), path, linenumber + 1);
                // appendLog("original method = " + b.toString());
                // appendLog("linenumber :  " + c);
                // appendLog("----------------------------------------------------------------------------");
            }
        }
    }
    
    public void getIndex2(String v, String path, int linenumber)
            throws IOException {
        int errorCodeStart = 0;
        int errorCodeEnd = 0;
        int errorCodeCount = 0;
        int leftCount = 0;
        int rightCount = 0;
        int leftStart = 0;
        int rightEnd = 0;
        for (int x = v.indexOf(methodName) + methodName.length(); x < v
                .length(); x++) {
            if ('"' == v.charAt(x)) {
                errorCodeCount++;
                if (1 == errorCodeCount) {
                    errorCodeStart = x;
                }
                else if (2 == errorCodeCount) {
                    errorCodeEnd = x;
                }
            }
            else if ('(' == v.charAt(x)) {
                leftCount++;
                if (1 == leftCount) {
                    leftStart = x;
                }
                if (leftCount == rightCount) {
                    rightEnd = x;
                    break;
                }
            }
            else if (')' == v.charAt(x)) {
                rightCount++;
                if (leftCount == rightCount) {
                    rightEnd = x;
                    break;
                }
            }
        }
        if (errorCodeEnd > errorCodeStart) {
            // does not contain error code or "()"
            // System.out.println(v + " leftStart = " + leftStart
            // + " rightEnd = " + rightEnd);
            String parameters = "";
            int parametersCount = 0;
            if (rightEnd > leftStart) {
                parameters = v.substring(leftStart + 1, rightEnd).replaceAll(
                        "\\(.*?\\)", "    ");
                // System.out.println("parameters = " + parameters);
                // appendLog("parameters = " + parameters);
                // System.out.println("the number of parameters = "
                // + parameters.split(",").length);
                // appendLog("the number of parameters = "
                // + parameters.split(",").length);
                parametersCount = parameters.split(",").length;
            }
            // errorCodeMap
            String errorcode = v.substring(errorCodeStart + 1, errorCodeEnd);
            
            if (null != errorCodeMap.get(errorcode)
                    && ((errorCodeMap.get(errorcode) + 1) != parametersCount && (errorCodeMap
                            .get(errorcode) + 4) != parametersCount)) {
                
                appendLog("====================================================== ");
                File f = new File(path);
                appendLog(f.getName());
                appendLog("method = " + v);
                appendLog("linenumber = " + linenumber);
                appendLog("parameters = " + parameters);
                appendLog("errorcode = " + errorcode + "   need "
                        + errorCodeMap.get(errorcode) + " parameters");
                appendLog("====================================================== ");
            }
        }
        else {
            appendLog("====================================================== ");
            File f = new File(path);
            appendLog(f.getName());
            appendLog("method = " + v);
            appendLog("linenumber = " + linenumber);
            appendLog("hava no error code");
            appendLog("====================================================== ");
        }
        
    }
    
    public void readErrorCode() {
        try {
            Files.lines(Paths.get(erroeCodeList)).forEach(line -> {
                getParametersCount(line);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private int getParametersCount(String a) {
        int count = 0;
        final char targetChar = '{';
        for (int i = 0; i < a.length(); i++) {
            if (targetChar == a.charAt(i)) {
                count++;
            }
        }
        errorCodeMap.put(a.substring(0, a.indexOf("#")).trim(), count);
        return count;
    }
}
