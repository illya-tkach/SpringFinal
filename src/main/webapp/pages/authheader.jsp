<%@ page contentType="text/html; charset=UTF-8" %>
<div class="authbar" >
    <div class="col-md-12">Login as: <strong>${loggedinuser}</strong> </div>
    <div class="col-md-7"><a href="<c:url value="/" />">На главную страницу</a></div>
    <div class="col-md-5" align="rigth"><a href="<c:url value="/logout" />">Logout</a></div>
    </table>
</div>