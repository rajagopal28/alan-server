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
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html lang="en">

<jsp:include page="../fragments/headTag.jsp"/>

<body>
<div class="container">
    <jsp:include page="../fragments/bodyHeader.jsp"/>

    <h2>Find Trips</h2>

    <spring:url value="/trips.html" var="formUrl"/>
    <form:form modelAttribute="trip" action="${fn:escapeXml(formUrl)}" method="get" class="form-horizontal"
               id="search-trip-form">
        <fieldset>
            <div class="control-group" id="title">
                <label class="control-label">Title </label>
                <form:input path="title" size="30" maxlength="80"/>
                <span class="help-inline"><form:errors path="*"/></span>
            </div>
            <div class="form-actions">
                <button type="submit">Find Trip</button>
            </div>
        </fieldset>
    </form:form>

    <br/>
    <a href='<spring:url value="/trips/new" htmlEscape="true"/>'>Add Trip</a>

    <jsp:include page="../fragments/footer.jsp"/>

</div>
</body>

</html>
