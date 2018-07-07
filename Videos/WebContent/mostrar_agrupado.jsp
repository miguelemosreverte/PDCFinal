<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"  import="java.util.LinkedList,ar.edu.ubp.pdc.beans.VideoBean" %>
    
    <%!
	
 	public String getCookieValue(Cookie[] cookies, String name) {
		for(Cookie c: cookies) {
			if(c.getName().equals(name)) {
				return c.getValue();
			}
		}
		return "";
	}

   %>
    
    
    <%
    LinkedList<VideoBean> videos = (LinkedList<VideoBean>)request.getAttribute("videos");
    LinkedList<String> categorias = (LinkedList<String>)request.getAttribute("categorias");
    %>  
    
    <%if(videos.isEmpty()) {%>
    No existen videos que satifagan el criterio de busqueda especificado
    <%}else{%>
    
  
    <% for(String categoria :categorias){ %>
       	<ul class="elemento-categoria" ><%=categoria%>
     <%for(VideoBean video :videos){
    		if(video.getNom_categoria().equals(categoria)){%>	
    		<li class="elemento-video" data-video-numero="<%=video.getNro_video()%>" data-video-link="<%=video.getLink_video()%>" data-video-cantante="<%=video.getCantante()%>" data-video-titulo="<%=video.getTitulo()%>">
    			<label class="titulo"><%=video.getTitulo()%></label>  <label class="cantante"><%=video.getCantante()%></label>
    			<%if( getCookieValue( request.getCookies(),Integer.toString(video.getNro_video()))!="") {%>
    			 [Visto]
    			<%}%>
    		</li>
    		<%}%>
     <%}%>
        </ul>
    <%}%>
  
   <%}%> 
   

 	
	   