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

    <h2>Trip Information</h2>

    <table class="table table-striped" style="width:600px;">
        <tr>
            <th>Title</th>
            <td><b><c:out value="${trip.title}"/></b></td>
        </tr>
        <tr>
            <th>Description</th>
            <td><c:out value="${trip.description}"/></td>
        </tr>
        <tr>
            <th>Trip Type</th>
            <td><c:out value="${trip.tripType}"/></td>
        </tr>
        <tr>
            <th>Place Id</th>
            <td><c:out value="${trip.placeId}"/></td>
        </tr>
        <tr>
            <th>Status</th>
            <td><c:out value="${trip.status}"/></td>
        </tr>
        <tr>
            <th>Planned On</th>
            <td><c:out value="${trip.plannedOn}"/></td>
        </tr>
        <tr>
            <th>Ends On</th>
            <td><c:out value="${trip.endsOn}"/></td>
        </tr>
         <tr>
            <td> 
            	<spring:url value="{tripId}/edit.html" var="editUrl">
                    <spring:param name="tripId" value="${trip.id}"/>
                </spring:url>
                <a href="${fn:escapeXml(editUrl)}" class="btn btn-info">Edit Trip</a></td>
            <td>
                <a href="${fn:escapeXml(addUrl)}"  class="btn btn-success">Add New Pet</a></td>
        </tr>
    </table>

    <jsp:include page="../fragments/footer.jsp"/>

</div>

</body>

</html>
