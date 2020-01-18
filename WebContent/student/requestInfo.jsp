<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% RequestBean req = (RequestBean) session.getAttribute("request"); %>

<!DOCTYPE html>
<html>

<%@ page import="java.util.*,project.Utils.Utils,project.Model.RequestBean"%>

<head>
	<meta charset="UTF-8">
	<title>Dettagli Richiesta giorno <%= req.getRequestDate() %> </title>
	<link rel="shortcut icon" href="<%=request.getContextPath() %>/img/favicon.ico" type="image/x-icon">
	<link rel="icon" href="<%=request.getContextPath() %>/img/favicon.ico" type="image/x-icon">
	
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
		.vertical-alignment-helper {
		    display:table;
		    height: 100%;
		    width: 100%;
		    pointer-events:none;
		}
		.vertical-align-center {
		    display: table-cell;
		    vertical-align: middle;
		    pointer-events:none;
		}
		.modal-content {
		    width:inherit;
		    max-width:inherit;
		    height:inherit;
		    margin: 0 auto;
		    pointer-events: all;
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
		
		<div class="container-fluid" style="margin-top: 25px;">
			<div class="alert alert-success" id="successDeleteDiv" style="display:none;" role="alert">Prenotazione eliminata con successo!</div>
			<div class="alert alert-danger" id="failureDeleteDiv" style="display:none;" role="alert">Eliminazione fallita!</div>
		</div>
		
		<div class="panel"></div>
		<% if(req.getState().equalsIgnoreCase("In valutazione")) { %>
			<div class="row text-center" style="margin-bottom: 25px;">				
				<div class="column">
					<a href="/TutoratoSmart/student/requestModify.jsp"><input class="btn btn-warning" id="modifyRequest" type="button" value="Modifica prenotazione"></a>
				</div>
				<div class="column">
					<button type="button" class="btn btn-danger" id="deleteButton">Elimina prenotazione</button>
 				</div>
 				<div class="column">
					<input class="btn btn-primary" type="button" id="back" value="Indietro" onClick="history.go(-1);return true;">
				</div>
			</div>
		<% } else {%>
			<div class="row text-center" style="margin-bottom: 25px;">
				<div class="column" style="width: 50%;">
					<button type="button" class="btn btn-danger" id="deleteButton">Elimina prenotazione</button>
 				</div>
 				<div class="column" style="width: 50%;">
					<input class="btn btn-primary" type="button" id="back" value="Indietro" onClick="history.go(-1);return true;">
				</div>
			</div>		
		<% } %>
		
		<div class="modal fade" id="deleteModal" role="dialog">
			 <div class="vertical-alignment-helper">
	        	<div class="modal-dialog vertical-align-center">
	            	<div class="modal-content">
	            		<div class="modal-header">
			          		<button type="button" class="close" data-dismiss="modal">&times;</button>
			          		<h4 class="modal-title">Elimina prenotazione</h4>
			       		</div>
				        <div class="modal-body">
				          	<div class="modal-body">
			          			<h5>Procedere con l'eliminazione?</h5>
			        		</div>
			        	</div>
			        	<div class="modal-footer">
			        		<div class="row text-center">
						  		<div class="column" style="width: 50%;"><input class="btn btn-danger" type="button" onclick="deleteRequest()" value="Elimina prenotazione"></div>
						  		<div class="column" style="width: 50%;"><button type="button" class="btn btn-primary" data-dismiss="modal">Annulla</button></div>
							</div>
				    	</div>
			    	</div>
			  	</div>
			</div>
		</div>
	</div>
	
	<input type="hidden" id="requestId" value="<%= req.getIdRequest() %>">
	
	<%@ include file="/partials/footer.jsp" %>
	
	<script src="<%= request.getContextPath() %>/js/studentScript.js"></script>
	<script>
		$(document).ready(function(){
			$("#deleteButton").click(function(){
				$("#deleteModal").modal();
			});
		});
	</script>
</body>
</html>