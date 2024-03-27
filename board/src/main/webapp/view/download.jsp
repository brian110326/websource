<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>

<%@ page import="java.io.BufferedOutputStream"%>
<%@ page import="java.io.FileInputStream"%>

<%
    // 클라이언트가 요청한 fileName 가져오기
    String fileName = request.getParameter("fileName");

    // 서버에 저장된 폴더의 경로 지정
    String fDownloadPath = "c:\\upload";
    String filePath = fDownloadPath + "\\" + fileName;

    // 하드디스크에 있는 파일 읽어오기
    // Stream 상기
    // input / output
    // input : 키보드 => System.in
    //         파일 => 텍스트 파일(Reader), 이미지 파일(FileInputStream), 동영상 파일(FileInputStream)
    FileInputStream in = new FileInputStream(filePath);

    // 브라우저가 응답할 때 읽어온 파일을 보내기
    // 어떤 종류의 파일인지 알려줘야함
    response.setContentType("application/octet-stream");

    fileName = new String(fileName.getBytes("utf-8"), "iso-8859-1");

    int start = fileName.lastIndexOf("_");
    String oriName = fileName.substring(start + 1);

    // ★★★★★★★★★★★★★★★★★★★★★★★★
    response.setHeader("Content-Disposition", "attachment;filename=" + oriName);

    out.clear();
    out = pageContext.pushBody();

    // BufferedStream() 괄호 안에 FileOutputStream 객체가 들어가지만 여기엔 대신 response.getOutputStream() 가 들어감
    // InputStream, OutputStream 다시 공부
    BufferedOutputStream buf = new BufferedOutputStream(response.getOutputStream());

    int numRead;
    byte b[] = new byte[4096];
    while ((numRead = in.read(b, 0, b.length)) != -1) {
        buf.write(b, 0, numRead);
    }

    buf.flush();
    buf.close();
    in.close();

%>