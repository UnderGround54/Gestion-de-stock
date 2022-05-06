<%@ include file="Entete.jsp"%>
<%@ page import="java.io.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%!

    public String join(ArrayList<?> arr, String del)
    {

        StringBuilder output = new StringBuilder();

        for (int i = 0; i < arr.size(); i++)
        {

            if (i > 0) output.append(del);
            
            // --- Quote strings, only, for JS syntax
            if (arr.get(i) instanceof String) output.append("\"");
            output.append(arr.get(i));
            if (arr.get(i) instanceof String) output.append("\"");
        }

        return output.toString();
    }
%>

<div class="row">
		<div class="col-lg-12">
			<h3 class="page-header">
				<i class="fa fa-bar-chart-o"> </i> Histogramme
			</h3>
			<ol class="breadcrumb">
				<li><i class="fa fa-home"></i><a href="index">  Home  </a></li>
				<li>_<i class="fa fa-list-alt"></i> Histogramme </li>
			</ol>
		</div>
</div>
<div class='container' style="width: 100%;margin-top: -20px;padding: 50px;">
    <script>
        <%
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            final String host = "jdbc:mysql://localhost:3306/gestionstock";
            final Connection con = DriverManager.getConnection(host, "root", "");
            String query = "select * from produit";
            final Statement st = con.createStatement();
            final ResultSet rs = st.executeQuery(query);

            ArrayList<String> design = new ArrayList<String>();
            ArrayList<Integer> stock = new ArrayList<Integer>();

            while(rs.next())
            {
            	design.add(rs.getString("design"));
            	stock.add(Integer.parseInt(rs.getString("stock")));
            }

            con.close();
        %>

        var nomData = [<%= join(design, ",") %>];
        var totalData = [<%= join(stock, ",") %>];
        
    </script>
    <script>
    window.onload = function()
    {
        zingchart.render
        ({
            id:"myChart",
            width:"100%",
            height:400,
            data:
            {
                "type":"line",
                "title":
                {
                    "text":"Produit en stock",
                    backgroundColor:'#be72e9',
                    "font-color" : "#fff"
                },
                "scale-x":
                {
                    "labels": nomData,
                    "text":"Sample tooltip with border",
                    
                    "line-color" : "#be72e9"
                },
                "scale-y":
                {
                    "text":"Sample tooltip with border",
                    
                    "line-color" : "#be72e9"
                },
                "plot":
                {
                    "line-width":2,
                    "line-color" : "#be72e9",
                    "animation" : {
                    	"effect" :"ANIMATION_SLIDE_LEFT"
                    }
                },
                "series":
                [
                    {
                      "values":totalData,
                      "tooltip":{
                    	  "border-radius" : 5
                      }
                    }
                ]
            }
        });
    };
    </script>
    <h1><center>Histogramme</center></h1>
    <hr>
    <br>
    <div id="myChart"></div>
    </div>

<%@ include file="Footer.jsp"%>