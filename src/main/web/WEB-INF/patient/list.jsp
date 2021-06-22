<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <title>Abernathy Clinic</title>
</head>

<body>
<div class="container">
    <div class="row" style="margin-left: 40%">
        <div
                class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
            <h1>Abernathy Clinic - Mediscreen</h1>
            <h2>Patient's List</h2>
        </div>
    </div>
    <div class="row" style="margin-left: 20%">
        <div class="form-group">
            <div>
                <a href="../home">Home</a>
            </div>
        </div>
        <div>
            <table class="table table-striped table-bordered" style="width: 100%" id="patientTable">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>LastName</th>
                        <th>FirstName</th>
                        <th>DateOfBirth</th>
                        <th>Gender</th>
                        <th>Address</th>
                        <th>Phone</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${patients}" var="patient">
                        <tr>
                            <td>${patient.id}</td>
                            <td>${patient.lastName}</td>
                            <td>${patient.firstName}</td>
                            <td>${patient.dateOfBirth}</td>
                            <td>${patient.gender}</td>
                            <td>${patient.address}</td>
                            <td>${patient.phone}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>