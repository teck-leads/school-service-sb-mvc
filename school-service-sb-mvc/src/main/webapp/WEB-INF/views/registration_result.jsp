<%@include file="common/header.jspf"%>
<%@include file="common/navigation.jspf"%>



<div class="container">


	<p>User registration page</p>


	<form:form modelAttribute="usersCmd" method="post">

		<fieldset class="form-group">
			<label>Registration ID</label>
			<form:input path="id" class="form-control" readonly="true" />

			<label>Name</label>
			<form:input path="name" class="form-control" required="required"
				oninvalid="this.setCustomValidity('Enter Name')"
				oninput="this.setCustomValidity('')" />
			<label>Age</label>
			<form:input path="age" class="form-control" required="required"
				oninvalid="this.setCustomValidity('Enter Age')"
				oninput="this.setCustomValidity('')" />

			<label>User Location</label>
			<form:input path="userLocation" class="form-control"
				required="required"
				oninvalid="this.setCustomValidity('Enter User Location')"
				oninput="this.setCustomValidity('')" />

			<label>Hospital Location</label>
			<form:input path="hopitalLocation" class="form-control"
				required="required"
				oninvalid="this.setCustomValidity('Enter Hospital Location')"
				oninput="this.setCustomValidity('')" />
		</fieldset>
		<input type="submit" value="Update" class="btn btn-outline-primary">
	</form:form>
</div>

<%@include file="common/footer.jspf"%>