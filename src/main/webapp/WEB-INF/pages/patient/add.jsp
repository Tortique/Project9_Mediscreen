<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns:th="http:/www.thymeleaf.org">

<head>
    <title>Abernathy Clinic</title>
</head>

<body>
<div class="container">
    <div class="row" style="margin-left: 40%">
        <div
                class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
            <h1>Abernathy Clinic - Mediscreen</h1>
            <h2>Add a new patient</h2>
        </div>
    </div>
    <div class="row" style="margin-left: 20%">
        <div class="form-group">
            <div>
                <a href="../home">Home</a>
            </div>
        </div>
        <div>
            <form th:action="/patient/add" modelattribute="patient" method="post" style="width: auto">
                <div>
                    <label>
                        <input type="lastName" name="lastName" class="form-control" placeholder="LastName" required/>
                    </label>
                </div>
                <div>
                    <label>
                        <input type="firstName" name="firstName" class="form-control" placeholder="FirstName" required/>
                    </label>
                </div>
                <div>
                    <label>
                        <input type="dateOfBirth" name="dateOfBirth" class="form-control" placeholder="DateOfBirth" required/>
                    </label>
                </div>
                <div>
                    <label>
                        <input type="gender" name="gender" class="form-control" placeholder="Gender" required/>
                    </label>
                </div>
                <div>
                    <label>
                        <input type="address" name="address" class="form-control" placeholder="Address"/>
                    </label>
                </div>
                <div>
                    <label>
                        <input type="phone" name="phone" class="form-control" placeholder="Phone"/>
                    </label>
                </div>
                <div>
                    <button class="btn btn-primary btn-block" type="submit">Add Patient</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>