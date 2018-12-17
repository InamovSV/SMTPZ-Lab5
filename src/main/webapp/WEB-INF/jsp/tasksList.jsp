<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
 <%--<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">--%>
     <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
           integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
     <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
           integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
     <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <title>Tasks</title>
</head>
<jsp:include page="menu.jsp" />
<body>
<br>
<div class="col-10 offset-1">
 <button type="button" class="btn btn-dark" data-toggle="modal" data-target="#exampleModalCenter">
     <i class="fas fa-plus"></i>
     Create new
 </button>

 <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
   <div class="modal-content">
    <div class="modal-header">
     <h5 class="modal-title" id="exampleModalLongTitle">Add new task</h5>
     <button type="button" class="close" data-dismiss="modal" aria-label="Close">
      <span aria-hidden="true">&times;</span>
     </button>
    </div>
    <form method="POST" action="/tasks/create">
    <div class="modal-body">
     <div class="form-group">
      <label for="label" class="col-form-label">Label:</label>
      <input type="text" name="label" class="form-control" id="label">
     </div>
     <div class="form-group">
      <label for="time" class="col-form-label">Time:</label>
     <input type="text" name="time" class="form-control" id="time">
     </div>
      <div class="form-group">
     <label for="isReady" class="col-form-label">Is ready:</label>
     <input type="number" name="isReady" class="form-control" id="isReady">
      </div>
     <div class="form-group">
     <label for="performer" class="col-form-label">Performer:</label>
     <input type="text" name="performer" class="form-control" id="performer">
   </div>
   </div>

     <div class="modal-footer">
      <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      <button class="btn btn-primary"  type="submit">Add</button>
    </div>
    </form>
   </div>
  </div>
 </div>

 <table class="table">
     <thead class="thead-dark">
  <tr>
   <th scope="col">Id</th>
   <th scope="col">Label</th>
   <th scope="col">Time</th>
   <th scope="col">Is ready</th>
   <th scope="col">Performer</th>
   <th scope="col">Action</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach  items="${tasks}" var ="task">
   <tr>
    <th scope="row">${task.id}</th>
    <td>${task.label}</td>
    <td>${task.time}</td>
    <td>${task.isReady ? "finished" : "unfinished"}</td>
    <td>${task.performer.fullname}</td>
    <td>
     <div>
      <a type="button" class="btn btn-warning" href="/tasks/${task.id}" style="margin-right: 10px">Detail</a>
         <c:if test="${!task.isReady}">
         <form action="/tasks/done/${task.id}" method="post">
             <input type="submit" value="done?" onclick="return confirm('Are you sure?');">
         </form>
         </c:if>
      <a type="button" class="btn btn-danger" href="/tasks/delete/${task.id}" >Delete</a>
     </div>
    </td>
   </tr>
  </c:forEach>
  </tbody>
 </table>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>
