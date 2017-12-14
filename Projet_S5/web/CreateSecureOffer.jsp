<%-- 
    Document   : CreateSecureOffer
    Created on : 12 déc. 2017, 20:53:57
    Author     : couli
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body class="row">
          <h1>Cloud Service Provider</h1>
          <form method="post" action="http://localhost:8084/Projet_S5/ProviderGatewayServlet" >
          <fieldset>
            <legend>Registration</legend>
            <br />
            <label for="consumerName">CSP name:</label>
            <input type="text" id="providerName" name="providerName"/>
            <br />
            
            <label for="price">Price:</label>
            <input type="text" name="price">
            
            <label for="service">Services & Security Level:</label>

            <table class="table table-hover  ">
                <thead>
                    <tr>
                        <th>#</th>
                        <th>Service level: </th>
                        <th>1</th>
                        <th>2</th>
                        <th>3</th>
                    </tr>
                </thead>
                <tbody>
                    <tr class="text-center">
                        <th scope="row">1</th>
                        <td><label for="compute">Computing Security Level</label></td>
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

                        <td><label for="compute">Networking Security Level</label></td>
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
                        <td><label for="compute">Storage Security Level</label></td>
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

           <input class="btn btn-primary" type="submit"  name="valider"  value="Register"/><br/>

        </fieldset>
    </form>
</body>
</html>