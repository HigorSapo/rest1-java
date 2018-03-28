package fiap.scj.rest;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/aluno")
public class AlunoResource {
	
	private static List<Aluno> listAlunos = new ArrayList<Aluno>();
	
	static {
		listAlunos.add(new Aluno("Higor", 123));
		listAlunos.add(new Aluno("Roberto", 124));
		listAlunos.add(new Aluno("Silva", 125));
	}
	
	@GET
	@Produces({
				MediaType.APPLICATION_JSON, 
				MediaType.APPLICATION_XML
			 })
	public List<Aluno> getAllAlunos() {
		List<Aluno> retorno = new ArrayList<Aluno>();
		
		retorno.add(new Aluno("Higor", 123));
		retorno.add(new Aluno("Roberto", 123));
		
		return retorno;
		
	}
	
	@GET
	@Path("/{ra}")
	@Produces({
				MediaType.APPLICATION_JSON, 
				MediaType.APPLICATION_XML
			 })
	public Aluno findAluno( @PathParam(value = "ra") Integer ra ) {
		
		for (Aluno aluno : listAlunos) {
			if (aluno.getRa().equals(ra)) {
				return aluno;
			}
		}
		return null;
		
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response createAluno(Aluno aluno) throws URISyntaxException {
		
		listAlunos.add(aluno);
		return Response
		.created(
				new URI("http://localhost:8080/rest/aluno/"+ aluno.getRa())).build(); 
				
	}
	
	
}
