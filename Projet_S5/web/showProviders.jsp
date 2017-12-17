<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="bean.CloudServiceConsumer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List of Providers</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
        <link rel="stylesheet" href="css/bootstrap.min.css"/>
    </head>
    <body>
        <h1></h1>
        
        
        <form method="POST" action="ContactProviders">
            <div id="accordion" role="tablist" class="mx-auto card text-center col-6">
            <c:forEach var="provider" items="${CSCObject.getListeProviders()}" >
                <div class="card">
                    <div class="card-header" role="tab" id="headingOne">
                        <h5 class="mb-0">
                            <label class="custom-control custom-checkbox mb-2 mr-sm-2 mb-sm-0">
                                <input type="checkbox" class="custom-control-input" name="${provider[0].getName().getName()}">
                                <span class="custom-control-indicator"></span>
                                <span class="custom-control-description">.</span>
                            </label>
                            <a data-toggle="collapse" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                <c:out value="${provider[0].getName().getName()}"></c:out>
                                </a>

                            </h5>
                        </div>

                        <div id="collapseOne" class="collapse show" role="tabpanel" aria-labelledby="headingOne" data-parent="#accordion">
                            <div class="card-body">
                                <div class="alert alert-dark" role="alert">
                                    <h5 class=""> 
                                    <c:out value="Service: " ></c:out>
                                    <c:out value="Level"></c:out>
                                    </h5>

                                </div>
                            <c:forEach var="providerService" items="${provider[0].getAllServices()}">
                                <div class="alert alert-light" role="alert">
                                    <c:out value="${providerService.getName()}"></c:out>
                                    :
                                    <c:out value="${providerService.getType()}"></c:out>
                                    </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </c:forEach>
                <input class="btn btn-primary col-6 mx-auto" type="submit" value="Send notification to providers">
        </div>
            
        </form>



        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>
        <script src="js/bootstrap.min.js"/>


    </body>
</html>
