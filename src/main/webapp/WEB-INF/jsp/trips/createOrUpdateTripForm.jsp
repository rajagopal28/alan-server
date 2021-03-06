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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<html lang="en">

<jsp:include page="../fragments/headTag.jsp"/>


<script>
    $(function () {
        $("#plannedOn").datepicker({ dateFormat: 'yy/mm/dd'});
        $("#endsOn").datepicker({ dateFormat: 'yy/mm/dd'});
    });
</script>
<body>
<div class="container">
    <jsp:include page="../fragments/bodyHeader.jsp"/>
    <c:choose>
        <c:when test="${trip['new']}"><c:set var="method" value="post"/></c:when>
        <c:otherwise><c:set var="method" value="put"/></c:otherwise>
    </c:choose>

    <h2>
        <c:if test="${trip['new']}">New </c:if> trip
    </h2>
    <form:form modelAttribute="trip" method="${method}" class="form-horizontal" id="add-trip-form">
        <petclinic:inputField label="Title" name="title"/>
        <petclinic:inputField label="Description" name="description"/>
        <petclinic:inputField label="Place Id" name="placeId"/>
        <petclinic:inputField label="Trip Type" name="tripType"/>
        <petclinic:inputField label="Status" name="status"/>
        <petclinic:inputField label="Planned On" name="plannedOn"/>
        <petclinic:inputField label="Ends On" name="endsOn"/>

        <div class="form-actions">
            <c:choose>
                <c:when test="${trip['new']}">
                    <button type="submit">Add trip</button>
                </c:when>
                <c:otherwise>
                    <button type="submit">Update trip</button>
                </c:otherwise>
            </c:choose>
        </div>
    </form:form>
</div>
<jsp:include page="../fragments/footer.jsp"/>
</body>

</html>
