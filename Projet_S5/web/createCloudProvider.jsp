<%-- 
    Document   : createCloudConsumer
    Created on : 27 nov. 2017, 22:33:27
    Author     : couli
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
        <link rel="stylesheet" href="css/bootstrap.min.css"/>
        <style type="text/css">
			table { display: inline-block; vertical-align: top; border: 1px solid; }
		</style>
    </head>
    <body >

        <div class="card text-center">
            <div class="card-header">
                <h1>Cloud Service Provider </h1>
            </div>
            <div class="card-body">
                <h4 class="card-title">Services & Security</h4>

                <form method="post" action="http://localhost:8084/Projet_S5/ProviderGatewayServlet" >
                    
                        <br />
                        <label for="consumerName">CSP name:</label>
                        <input type="text" id="providerName" name="providerName"/>
                        <br />

                        <label for="price">Price:</label>
                        <input type="text" name="price">
                        <br/>

                        <div>
                        <table class="table" style="width:350px">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Services level: </th>
                                    <th>1</th>
                                    <th>2</th>
                                    <th>3</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr class="text-center">
                                    <th scope="row">1</th>
                                    <td><label for="compute">Compute</label></td>
                                    <td>
                                        <span class="radio radio-info radio-inline">
                                            <input type="radio" id="computeRadioLevel3" value="1" name="computeRadio" checked=""/>
                                        </span>
                                    </td>
                                    <td>
                                        <span class="radio radio-inline">
                                            <input type="radio" id="computeRadioLevel3" value="2" name="computeRadio"/>
                                        </span>
                                    </td>
                                    <td><span class="radio radio-inline">
                                            <input type="radio" id="computeRadioLevel3" value="3" name="computeRadio"/>

                                        </span>
                                    </td>
                                </tr>
                                <tr class="text-center">
                                    <th scope="row">2</th>

                                    <td><label for="compute">Network</label></td>
                                    <td>
                                        <span class="radio radio-info radio-inline">
                                            <input type="radio" id="computeRadioLevel3" value="1" name="networkRadio" checked=""/>
                                        </span>
                                    </td>
                                    <td>
                                        <span class="radio radio-inline">
                                            <input type="radio" id="computeRadioLevel3" value="2" name="networkRadio"/>
                                        </span>
                                    </td>
                                    <td><span class="radio radio-inline">
                                            <input type="radio" id="computeRadioLevel3" value="3" name="networkRadio"/>

                                        </span>
                                    </td>
                                </tr>
                                <tr>
                                    <th scope="row">3</th>
                                    <td><label for="compute">Storage</label></td>
                                    <td>
                                        <span class="radio radio-info radio-inline">
                                            <input type="radio" id="computeRadioLevel3" value="1" name="storageRadio" checked=""/>
                                        </span>
                                    </td>
                                    <td>
                                        <span class="radio radio-inline">
                                            <input type="radio" id="computeRadioLevel3" value="2" name="storageRadio"/>
                                        </span>
                                    </td>
                                    <td><span class="radio radio-inline">
                                            <input type="radio" id="computeRadioLevel3" value="3" name="storageRadio"/>

                                        </span>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                            
                            <table class="table" style="width:400px">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Services Security level: </th>
                                    <th>1</th>
                                    <th>2</th>
                                    <th>3</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr class="text-center">
                                    <th scope="row">1</th>
                                    <td><label for="compute">Compute Security</label></td>
                                    <td>
                                        <span class="radio radio-info radio-inline">
                                            <input type="radio" id="computeSecureRadioLevel3" value="1" name="computeSecureRadio" checked=""/>
                                        </span>
                                    </td>
                                    <td>
                                        <span class="radio radio-inline">
                                            <input type="radio" id="computeSecureRadioLevel3" value="2" name="computeSecureRadio"/>
                                        </span>
                                    </td>
                                    <td><span class="radio radio-inline">
                                            <input type="radio" id="computeSecureRadioLevel3" value="3" name="computeSecureRadio"/>

                                        </span>
                                    </td>
                                </tr>
                                <tr class="text-center">
                                    <th scope="row">2</th>

                                    <td><label for="compute">Network Security</label></td>
                                    <td>
                                        <span class="radio radio-info radio-inline">
                                            <input type="radio" id="computeSecureRadioLevel3" value="1" name="networkSecureRadio" checked=""/>
                                        </span>
                                    </td>
                                    <td>
                                        <span class="radio radio-inline">
                                            <input type="radio" id="computeSecureRadioLevel3" value="2" name="networkSecureRadio"/>
                                        </span>
                                    </td>
                                    <td><span class="radio radio-inline">
                                            <input type="radio" id="computeSecureRadioLevel3" value="3" name="networkSecureRadio"/>

                                        </span>
                                    </td>
                                </tr>
                                <tr>
                                    <th scope="row">3</th>
                                    <td><label for="compute">Storage Security</label></td>
                                    <td>
                                        <span class="radio radio-info radio-inline">
                                            <input type="radio" id="computeSecureRadioLevel3" value="1" name="storageSecureRadio" checked=""/>
                                        </span>
                                    </td>
                                    <td>
                                        <span class="radio radio-inline">
                                            <input type="radio" id="computeSecureRadioLevel3" value="2" name="storageSecureRadio"/>
                                        </span>
                                    </td>
                                    <td><span class="radio radio-inline">
                                            <input type="radio" id="computeSecureRadioLevel3" value="3" name="storageSecureRadio"/>

                                        </span>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        

                    </div>
                      

                        <input class="btn btn-primary" type="submit"  name="valider"  value="Register"/><br/>

                </form>
            </div>
        </div>


        
        <script src="js/bootstrap.js"/>
            <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
            <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>
    </body>
</html>
