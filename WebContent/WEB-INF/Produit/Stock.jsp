<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../Entete.jsp"%>
<c:set var="entree" value="0" />
<c:set var="sortie" value="0" />
<c:set var="stock" value="0" />
<script type="text/javascript">
	function printFacture(element) {
		var page = document.body.innerHTML;
		var imprimer = document.getElementById(element).innerHTML;
		document.body.innerHTML = imprimer;
		window.print();
		document.body.innerHTML = page;
	}
</script>
<div class="row">
	<div class="col-lg-12">
		<h3 class="page-header">
			<i class="fa fa-signal"></i> MOUVEMENT DE STOCK
		</h3>
		<ol class="breadcrumb">
			<li><i class="fa fa-home"></i><a href="index"> Home</a></li>
			<li>_<i class="fa fa-list-alt"></i> Stock entrée et sortie</li>
		</ol>
	</div>
</div>
<div class="d-sm-flex align-items-center justify-content-between">
	<div class="col-lg-3">
		<div class="nav search-row">
			<form class="navbar-form" method="#" action="#">
				<div class="input-group">
					<input class="form-control" value="" name="search"
						placeholder="Search" type="text" id="quick">
				</div>
			</form>
		</div>

	</div>
	<div class="col-lg-3">
		<form class="navbar-form" method="get" action="ControleProduit">
			<div class="input-group">
				<select class="form-control" data-toggle="dropdown"
					id="searchnumprod" name="searchnumprod">
					<option value="">tous</option>
					<c:forEach var="produit" items="${listeproduit}">																
						<option value="${produit.getNumProduit()}">${produit.getDesign()}</option>
					</c:forEach>
				</select>
				<button data-backdrop="false" value="searchproduit" name="resultat"
					class="btn btn-lg btn-sm"
					style="background-color: #be72e9; color: white;">
					<div class="fa fa-search"></div>
				</button>
			</div>
		</form>	
	</div>
	<div class="col-lg-6">
		<div class="nav search-row"  style="margin: 0% 0% 1% 40%;">
			<form class="navbar-form" method="get" action="ControleProduit">
				<div class="input-group">
					<input class="form-control" value="" name="date1"
						placeholder="Search" type="date" id="date1"> <input
						class="form-control" value="" name="date2" placeholder="Search"
						type="date" id="date2">
					<button data-backdrop="false" value="searchdate" name="resultat"
						class="btn btn-lg btn-sm"
						style="background-color: #be72e9; color: white;">
						<div class="fa fa-search"></div>
					</button>
				</div>
			</form>
		</div>
	</div>
</div>
<c:forEach var="stockprod" items="${stockprod}">
<div class=""  style="margin: 0% 0% 1% 28%;">
	<h5>DESIGNATION : ${stock = stockprod.getDesign()}</h5>														
	<h5>STOCK ACTUEL : ${stock = stockprod.getStock()}</h5>
</div>
</c:forEach>								
<div id="facture"margin-top: 2%;">
	<div class="row loading">
		<div class="col-lg-12">
			<h1 id="somm"></h1>
			<section class="panel">
				<header class="panel-heading"></header>
				<table class="table table-striped table-advance table-hover">
					<thead>
						<tr>
							<th><div class="fa fa-bars"></div> Numéro Bon</th>
							<th><div class="fa fa-mail-forward"></div> Entrée</th>
							<th><div class="fa fa-reply"></div> Sortie</th>
							<th><div class="fa fa-calendar"></div> date</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="stockentree" items="${listeStockEntree}">
							<tr>
								<td>${stockentree.getNumBe()}</td>
								<td>${stockentree.getQteEntree()}</td>
								<td><input type="hidden"
									value="${entree = entree + stockentree.getQteEntree()}"></td>
								<td>${stockentree.getDateEntree()}</td>
							</tr>
						</c:forEach>

						<c:forEach var="stocksortie" items="${listeStockSortie}">
							<tr>
								<td>${stocksortie.getNumBs()}</td>
								<td><input type="hidden"
									value="${sortie = sortie + stocksortie.getQteSortie()}">
									</td>
									
								<td>${stocksortie.getQteSortie()}</td>
								<td>${stocksortie.getDateSortie()}</td>
							</tr>
						</c:forEach>
						<tr>
							<td></td>
							<td>Total Entrée : ${entree}</td>
							<td>Total Sortie : ${sortie}</td>
							<td></td>
						</tr>
					</tbody>
				</table>
			</section>
		</div>
	</div>
</div>
<button onclick="printFacture('facture');" class="btn" style="margin-top: 2%; margin-left: 1%; background-color: #be72e9; color: white;">
<div class="fa fa-file-pdf-o">
</div>
</button>
<script type="text/javascript">
	var url = window.location.href;
	var chaine = "http://localhost:8081/GestionStock/Stock";
	if (url == "http://localhost:8081/GestionStock/ControleProduit?date1=&date2=&resultat=searchdate")
		window.location.href = chaine;
</script>


<%@ include file="../Footer.jsp"%>