<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.DAO.PapersDaoImpl"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Delete Paper</title>
</head>
<body>
<h1>Delete Paper</h1>
<form method="post">
    <label for="paperId">Enter Paper ID to delete:</label>
    <input type="text" id="paperId" name="paperId" required><br>

    <input type="submit" value="Delete">
</form>

<%
    // 检查是否有提交的表单数据
    if ("POST".equalsIgnoreCase(request.getMethod())) {
        // 获取表单提交的试卷 ID
        String paperIdStr = request.getParameter("paperId");

        // 检查试卷 ID 是否为空
        if (paperIdStr != null && !paperIdStr.isEmpty()) {
            try {
                // 将试卷 ID 转换为整数
                int paperId = Integer.parseInt(paperIdStr);

                // 创建 PapersDaoImpl 对象
                PapersDaoImpl papersDao = new PapersDaoImpl();

                // 删除试卷
                papersDao.deleteById(paperId);

                // 显示删除成功信息
                out.println("<p>Paper with ID " + paperId + " has been deleted successfully.</p>");
            } catch (NumberFormatException e) {
                // 显示试卷 ID 格式错误信息
                out.println("<p>Invalid Paper ID format. Please enter a valid integer.</p>");
            } catch (Exception e) {
                // 显示删除失败信息
                out.println("<p>Error occurred while deleting paper. Please try again later.</p>");
                e.printStackTrace();
            }
        } else {
            // 显示试卷 ID 为空错误信息
            out.println("<p>Please enter Paper ID to delete.</p>");
        }
    }
%>
</body>
</html>

