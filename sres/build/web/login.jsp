<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>SRES » login</title>
        <link href="/sres/stylesheets/login.css" media="screen" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <div id="login-wrapper">
            <div id="login">
                <form action="/sres/auth" method="post">
                    <p><label>Login or email</label></p>
                    <p><input class="input" id="email" name="email" type="text" /></p>
                    <p><label>Password</label></p>
                    <p><input class="input" id="password" name="password" value="" type="password" /></p>
                    <br />
                    <p><input class="button" name="commit" value="Log in" type="submit" /></p>
                </form>
            </div><!-- #login-->
            <br class="clear" />
            <div class="login-footer">
                <span>© 2009 <a href="http://www.cuc.edu.co/">SRES</a> All rights reserved.</span>
            </div>
        </div><!-- #login-wrapper -->
    </body>
</html>
