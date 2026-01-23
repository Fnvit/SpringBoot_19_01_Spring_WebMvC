<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>사람들 정보 수정/삭제/삽입 창</h1>
    <b style="color: red;">${message}</b>
    <form action="/regist" method="post">
        이름: <input type="text" name="name"> <br>
        나이: <input type="text" name="age"> <br>
        <button>회원가입</button>
    </form>

    <form action="/update" method="post">
        이름: <input type="text" name="name"> <br>
        나이: <input type="text" name="age"> <br>
        <button>수정</button>
    </form>

    <form action="/delete" method="post">
        이름: <input type="text" name="name"> <br>
        <button>삭제</button>
    </form>
    
    
</body>
</html>
