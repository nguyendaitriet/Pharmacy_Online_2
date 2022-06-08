<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 07-Jun-22
  Time: 3:55 PM
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Register</title>
</head>
<body>

<h3>Register Account</h3>
<form method="post" >
    <table cellpadding="2" cellspacing="2">
        <tr>
            <td>Name Drug</td>
            <td><input type="text" name="drug" value="${drug.getDrugName() }"></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="number" name="quantity" value="${drug.getQuantity() }"></td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td><input type="submit" value="Save"></td>
        </tr>
    </table>

    <div>
        ${errors}
    </div>
</form>

</body>
</html>
