<!DOCTYPE html>
<!--
    Copyright 2002-2013 the original author or authors.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
-->

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>

<html lang="en">

<jsp:include page="../fragments/headTag.jsp"/>

<body>
<div class="container">
    <jsp:include page="../fragments/bodyHeader.jsp"/>

    <h2>User Information</h2>

    <table class="table table-striped" style="width:600px;">
        <tr>
            <th>Name</th>
            <td><b><c:out value="${user.firstName} ${user.lastName}"/></b></td>
        </tr>
        <tr>
            <th>Address</th>
            <td><c:out value="${user.address}"/></td>
        </tr>
        <tr>
            <th>City</th>
            <td><c:out value="${user.city}"/></td>
        </tr>
        <tr>
            <th>Telephone</th>
            <td><c:out value="${user.telephone}"/></td>
        </tr>
         <tr>
            <td> 
            	<spring:url value="{userId}/edit.html" var="editUrl">
                    <spring:param name="userId" value="${user.id}"/>
                </spring:url>
                <a href="${fn:escapeXml(editUrl)}" class="btn btn-info">Edit User</a></td>
            <td>
                <a href="${fn:escapeXml(addUrl)}"  class="btn btn-success">Add New Pet</a></td>
        </tr>
    </table>

    <jsp:include page="../fragments/footer.jsp"/>

</div>

</body>

</html>
