<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% RequestBean req = (RequestBean) session.getAttribute("request"); %>

<!DOCTYPE html>
<html>

<%@ page import="java.util.*,project.Utils.Utils,project.Model.RequestBean"%>

<head>
	<meta charset="UTF-8">
	<title>Dettagli Richiesta giorno <%= req.getRequestDate() %> </title>
	
	<style>
	    table,th,td{
	    	border: 1px solid black;
	    	border-collapse: collapse;
	    	text-align:center;
	    }
	    th {
	    	background-color:#4d94ff;
	     	color: white;
	    }
	    th,td {
	    	padding:8px;
	    }
	    .column {
		  float: left;
		  width: 33%;
		}	    
    </style>	
</head>

<body>
	<%@ include file="/partials/header.jsp" %>
	
	<div class="content text-center" style="width: 70%;margin: 0 auto; margin-top: 50px; border: 2px solid #232F3E; border-radius:8px;">
		<div class="panel">
			<h2 align="center">Dettagli richiesta di appuntamento per il giorno <%= req.getRequestDate() %></h2>
		</div>
		
		<div id="requestDiv" style="margin: 25px;">
			<table style="width: 95%;margin: 0 auto;margin-bottom: 25px">
				<tr>					
		    		<th class="text-center">Data</th>
		    		<th class="text-center">Orario</th>
		    		<th class="text-center">Stato</th>
	    		</tr>	    		
				<tr>
				 	<td><%= req.getRequestDate() %></td>				 			
				 	<td><%= Utils.getTimeAsString(req.getRequestTime()) %></td>
				 	<td><%= req.getState() %></td>
				</tr>
				
				<tr>
					<th class="text-center" colspan="3">Commento</th>
				</tr>				
				<tr>
					<td colspan="3"><%= req.getStudentComment() %></td>
				</tr>
			</table>
		</div>
		
		<div class="panel"></div>
		<% if(req.getState().equalsIgnoreCase("In valutazione")) { %>
			<div class="row text-center" style="margin-bottom: 25px;">				
				<div class="column">
					<a href="<%= response.encodeURL("tutor/requestModify.jsp") %>"><input class="btn btn-primary" id="modifyRequest" type="button" value="Modifica prenotazione"></a>
				</div>
				<div class="column">
					<button type="button" class="btn btn-danger" id="deleteButton">Elimina prenotazione</button>
 				</div>
 				<div class="column">
					<input class="btn btn-primary" type="button" value="Indietro" onClick="history.go(-1);return true;">
				</div>
			</div>
		<% } else {%>
			<div class="row text-center" style="margin-bottom: 25px;">
				<div class="column" style="width: 50%;">
					<button type="button" class="btn btn-danger" id="deleteButton">Elimina prenotazione</button>
 				</div>
 				<div class="column" style="width: 50%;">
					<input class="btn btn-primary" type="button" value="Indietro" onClick="history.go(-1);return true;">
				</div>
			</div>		
		<% } %>
		
		<div class="modal fade" id="deleteModal" role="dialog">
		    <div class="modal-dialog">
		        <div class="modal-content">
			        <div class="modal-header">
			          	<button type="button" class="close" data-dismiss="modal">&times;</button>
			          	<h4 class="modal-title">Elimina prenotazione</h4>
			        </div>
			        <div class="modal-body">
			          	<p>Procedere con l'eliminazione?</p>
			        </div>
			        <div class="modal-footer">
			        	<div class="row text-center">
						  <div class="column" style="width: 50%;"><a href="<%= response.encodeURL(request.getContextPath() +  "/Request?flag=2&delete=true&id=" + req.getIdRequest()) %>"><input class="btn btn-danger"  id="deleteActivityButton" type="button" value="Elimina prenotazione"></a></div>
						  <div class="column" style="width: 50%;"><button type="button" class="btn btn-primary" data-dismiss="modal">Annulla</button></div>
						</div>
				    </div>
		    	</div>						      
			</div>
		</div>
	</div>
	
	<%@ include file="/partials/footer.jsp" %>
	
	<script src="<%= request.getContextPath() %>/js/commissionScript.js"></script>
	<script>
		$(document).ready(function(){
		  $("#deleteButton").click(function(){
		    $("#deleteModal").modal();
		  });
		});
	</script>
</body>
</html>