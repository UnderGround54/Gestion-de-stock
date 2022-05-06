<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../Entete.jsp"%>
<div class="row">
	<div class="col-lg-12">
		<h3 class="page-header">
			<i class="fa fa-bookmark"></i> LIGNE DE BON D'ENTREE
		</h3>
		<ol class="breadcrumb">
			<li><i class="fa fa-home"></i><a href="index"> Home </a></li>
			<li>_<i class="fa fa-list-alt"> </i> Liste Ligne De Bon D'entrée</li>
		</ol>
	</div>
</div>
<div class="d-sm-flex align-items-center justify-content-between mb-4">
	<div class="col-lg-6">
		<div id="html">
			<button data-toggle="modal" onclick="ajouter(this)"
				data-backdrop="false" href="#formulaireligne"
				class="btn btn-lg btn-sm" style="background-color: #be72e9; color: white;">
				<div class="fa fa-plus"></div>
				AJOUTER
			</button>
		</div>
	</div>
	<div class="col-lg-6" style="margin: 0% 0% 1% 25%;">
		<div class="nav search-row">
			<form class="navbar-form" method="#" action="">
				<div class="input-group">
					<input class="form-control" value="" name="search"
						placeholder="Search" type="text" id="quick">
					<button data-backdrop="false" value="" name="resultat"
						class="btn btn-lg btn-sm" style="background-color: #be72e9; color: white;">
						<div class="fa fa-search"></div>
					</button>
				</div>
			</form>
		</div>
	</div>
</div>
<div class="row">
	<div class="col-lg-12">
		<section class="panel">
			<header class="panel-heading"></header>
			<table class="table table-striped table-advance table-hover">
				<thead>
					<tr>
						<th><div class="fa fa-bars"></div> Numéro Be</th>
						<th><div class="fa fa-signal"></div> Produit</th>
						<th><div class="fa fa-mail-forward"></div> Qte Entrée</th>
						<th><div class="fa fa-cogs"></div> Action</th>
					</tr>
				</thead>
				<thead>
				</thead>
				<tbody>

					<c:forEach var="lignebe" items="${listelignebe}">
						<tr>
							<td>${lignebe.getNumBe()}</td>
							<td>${lignebe.getDesignProduit()}</td>
							<td>${lignebe.getQteEntree()}</td>
							<td>
								<button id="btn_modif" data-toggle="modal" data-backdrop="false"
									value="${lignebe.getIdLigneBe()}+${lignebe.getNumBe()}+${lignebe.getNumProduit()}+${lignebe.getQteEntree()}"
									href="#formulaireligne"
									class="btn btn-circle btn-sm fa fa-edit"
									onclick="modifier(this)" style="background-color: gray; color: white;"></button>

								<button
									value="${lignebe.getIdLigneBe()}+${lignebe.getNumProduit()}+${lignebe.getQteEntree()}"
									onclick="supprimer(this)"
									class="btn btn-circle btn-sm fa fa-trash-o"
									href="#formulaireConfirmation" style="background-color: gray; color: white;" data-toggle="modal"
									data-backdrop="false"></button>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</section>
	</div>
</div>

<!-- MODALE BEGIN  Ligne -->
<div class="col-md-6 col-xs-12 col-md-offset-3">
	<div class="modal fade" id="formulaireligne">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						style="color: red;">x</button>
				</div>
				<div class="modal-body">
					<div class="panel-body">
						<div class="form">
							<form name="form" action="ControleLigneBe"
								class="form-validate form-horizontal" id="feedback_form"
								method="post">
								<div class="form-group">
									<input type="hidden" name="idlignebe" id="idlignebe" value=""
										class="form-control "> <label for=""
										class="control-label">Numéro Be :</label> <select
										class="form-control" data-toggle="dropdown" id="numbe"
										name="numbe">
										<c:forEach var="bonentree" items="${listebe}">
											<option value="${bonentree.getNumBe()}">${bonentree.getNumBe()}</option>
										</c:forEach>
									</select> <label for="" class="control-label">Numéro Produit :</label> <select
										class="form-control" data-toggle="dropdown" id="numproduit"
										name="numproduit">
										<c:forEach var="produit" items="${listeproduit}">
											<option value="${produit.getNumProduit()}">${produit.getDesign()}</option>
										</c:forEach>
									</select> <label for="" class="control-label">Qte Entrée :</label>
									<div class="input-group">
										<span class="input-group-addon"><i
											class="glyphicon glyphicon-user"></i></span> <input type="text"
											name="qteentree" id="qteentree" value=""
											class="form-control ">
									</div>

									<input type="hidden" id="add_edit" name="ajoutlignebe"
										value="addlignebe">
									<button type="submit" name="ajoutlignebe" id="button"
										class="btn btn-success" style="margin-top: 10px;">
										<span class="fa fa-send">Ajouter</span>
									</button>

								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- MODALE END Ligne -->

<!-- MODALE BEGIN SUPPRESSION -->
<div class="col-md-3 col-xs-6 col-md-offset-3">
	<div class="modal fade" id="formulaireConfirmation">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						style="color: red;">x</button>
				</div>
				<div class="modal-body">
					<div class="panel-body">
						<h3>Voulez-vous vraiment supprimer?</h3>
					</div>
				</div>

				<div class="modal-footer">
					<a id="supp"
						href="ControleLigneBe?resultat=suprrlignebe&amp;idlignebe=&amp;numproduit=&amp;qteentree="
						class="btn btn-success btn-sm">Confirmer</a>
					<button class="btn btn-danger btn-sm" data-dismiss="modal">Annuler</button>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- MODALE END -->

<script type="text/javascript">
	function supprimer(champ) {
		var chaine = champ.value;
		var liste = chaine.split("+");
		$('#supp').attr(
				'href',
				"ControleLigneBe?resultat=suprrlignebe&idlignebe=" + liste[0]
						+ "&numproduit=" + liste[1] + "&qteentree=" + liste[2]);
	}

	function ajouter() {
		document.getElementById("button").innerText = "Ajouter";
		document.getElementById("idlignebe").value = "";
		document.getElementById("qteentree").value = "";
		//document.getElementById("numbe").value = "";
		document.getElementById("add_edit").value = "addlignebe";
	}

	function modifier(champ) {
		var chaine = champ.value;
		var liste = chaine.split("+");
		document.getElementById("idlignebe").value = liste[0];
		document.getElementById("numbe").value = liste[1];
		document.getElementById("numproduit").value = liste[2];
		document.getElementById("qteentree").value = liste[3];
		document.getElementById("add_edit").value = "modiflignebe";
		document.getElementById("button").innerText = "Modifier";
	}
</script>

<%@ include file="../Footer.jsp"%>