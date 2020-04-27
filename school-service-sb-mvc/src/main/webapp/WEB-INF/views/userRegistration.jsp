<%@include file="common/header.jspf"%>
<%@include file="common/navigation.jspf"%>

<div class="container">



	<p>User Registration</p>

	<form:form modelAttribute="usersCmd" method="post" id="form1">
	
	<form:hidden path="stateFlag" id="stateFlagId"/>
	
		<fieldset class="form-group">
			<c:if test="${idKey ne 0}">
				<label>Registration ID</label>
				<form:input path="id" class="form-control" readonly="true" />
			</c:if>


			<label>Name</label>
			<form:input path="name" class="form-control" required="required"
				oninvalid="this.setCustomValidity('Enter Name')"
				oninput="this.setCustomValidity('')" />
			<label>Age</label>
			<form:input path="age" class="form-control" required="required"
				oninvalid="this.setCustomValidity('Enter Age')"
				oninput="this.setCustomValidity('')" />

			<label for="exampleFormControlSelect1">State</label>
			<form:select class="form-control" id="stateSelect"
				path="state" required="required"
				oninvalid="this.setCustomValidity('Enter State')"
				oninput="this.setCustomValidity('')" >

				<form:option value="">Select</form:option>
				<c:forEach items="${statesKey }" var="state">

					<form:option value="${state.code }">${state.name }</form:option>
				</c:forEach>




			</form:select>

	<div  style="display: ${showHideCity};">
			<label>City</label>
			<form:input path="capital" class="form-control" readonly="true"/>
</div>



		</fieldset>


		<c:if test="${idKey eq 0}">
			<input type="submit" value="Register" class="btn btn-outline-primary">
			<input type="reset" value="Reset" class="btn btn-outline-dark">

		</c:if>

		<c:if test="${idKey gt 0}">
			<input type="submit" value="Update" class="btn btn-outline-primary">
		</c:if>

	</form:form>
</div>

<%@include file="common/footer.jspf"%>