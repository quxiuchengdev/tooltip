<%--
  User: quxiucheng
  Date: 2016/12/8
  Time: 10:03
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta charset="utf-8" />
    <title>重复提交异常</title>

    <meta name="description" content="重复提交异常" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
    <meta name="menu" content="${exceptionMenuPath}" />
</head>
<body>

    <div class="error-container">
        <div class="well">
            <h1 class="grey lighter smaller">
                <span class="blue bigger-125">
                    <i class="ace-icon fa fa-random"></i>
                    500
                </span>
                不要重复提交
            </h1>

            <hr />
            <h3 class="lighter smaller">
                But we are working
                <i class="ace-icon fa fa-wrench icon-animated-wrench bigger-125"></i>
                on it!
            </h3>

            <div class="space"></div>

            <div>
                <h4 class="lighter smaller">Meanwhile, try one of the following:</h4>

                <ul class="list-unstyled spaced inline bigger-110 margin-15">
                    <li>
                        <i class="ace-icon fa fa-hand-o-right blue"></i>
                        Read the faq
                    </li>

                    <li>
                        <i class="ace-icon fa fa-hand-o-right blue"></i>
                        Give us more info on how this specific error occurred!
                    </li>
                </ul>
            </div>

            <hr />
            <div class="space"></div>

            <div class="center">
                <a href="javascript:history.back()" class="btn btn-grey">
                    <i class="ace-icon fa fa-arrow-left"></i>
                    Go Back
                </a>

                <a href="#" class="btn btn-primary">
                    <i class="ace-icon fa fa-tachometer"></i>
                    Dashboard
                </a>
            </div>
        </div>
    </div>

</body>
</html>
