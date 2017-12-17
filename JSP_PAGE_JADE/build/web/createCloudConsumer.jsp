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
    </head>
    <body class="row">
          <h1>Cloud Service Consumer</h1>
          <form method="post" action="http://localhost:8084/JADE_JSP/GatewayServlet" >
          <fieldset>
            <legend>Inscription</legend>
            <br />
            <label for="consumerName">CSC name:</label>
            <input type="text" id="consumerName" name="consumerName"/>
            <br />
            <label for="service">Services:</label>

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
                                <input type="radio" id="computeRadioLevel3" value="c3" name="computeRadio"/>

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

            <input class="btn btn-primary" type="submit"  name="valider"  value="Find"/><br/>

        </fieldset>
    </form>
</body>
</html>
