<%@include file="common/header.jspf"%>
<%@include file="common/navigation.jspf"%>
<div class="container">

	<table class="table table-striped">
		<thead>
			<tr>
				<th>Registration ID</th>
				<th>Name</th>
				<th>Age</th>
				<th>State</th>
				<th>Capital</th>
				<th>Edit</th>
				<th>Remove</th>
			</tr>
		</thead>
		<tbody>
		
			<c:forEach items="${usersList }" var="user">
				<tr>
					<td>${user.id}</td>
					<td>${user.name}</td>
					<td>${user.age}</td>
					<td>${user.state}</td>
					<td>${user.capital}</td>
					<td><a href="/school-service/users/edit/${user.uuidCode}" 
						class="btn btn-info">Update</a>
						<!-- class="btn btn-info" id="updateId">Update</a> -->
						<%-- <form method="post" action="edit" id="updateFormId">
						<input type="hidden" value="${user.id}" id="updateUserId">
							<input type="submit" style="display: none;"/>
						</form> --%>
						
						
						</td>
						
						
						
						
						
						
						
					<td>
						<%-- <a href="/school-service/users/delete/${user.id}" class="deleteRecord">Delete
					 <span class="glyphicon glyphicon-remove"></span>
					</a> --%> <%-- <a href="/school-service/users/delete/${user.id}" data-toggle="modal" data-target="#exampleModal">Delete
					</a> --%> <a href="#"
						data-href="/school-service/users/delete/${user.uuidCode}"
						data-toggle="modal" data-target="#confirm-delete"
						class="btn btn-danger">Delete</a>
					</td>
				</tr>
			</c:forEach>
			
		</tbody>
	</table>





</div>

<%@include file="common/footer.jspf"%>