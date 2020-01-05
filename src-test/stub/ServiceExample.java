package stub;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import annotation.Secured;

@Tag(name = "Gestion_CCRPvse", description = "Gestion des PVSE")
@Path("/pvse")
@Secured
public interface ServiceExample extends ServiceInterface<ServiceExampleDtoInterface> {

	@Path("/getPvseCompleteByKey")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Tag(name = "Gestion_CCRPvse", description = "Gestion des PVSE")
    @Operation(operationId = "getPvseCompleteByKey", summary = "getPvseCompleteByKey", description = "getPvseCompleteByKey")
    @APIResponse(responseCode = "200", description = "Recherche OK"/*, content = @Content(schema = @Schema(implementation = CCRPvseDto.class))*/)
    @APIResponse(responseCode = "204", description = "CCRPvseDtoInterface Erreur")
    @APIResponse(responseCode = "400", description = "Paramètres incorrects.")
    @APIResponse(responseCode = "401", description = "Accès non autorisé.")
    @APIResponse(responseCode = "403", description = "Accés refusé.")

    @APIResponse(responseCode = "404", description = "Ressource non trouvée.")

    @APIResponse(responseCode = "500", description = "Une erreur s'est produite. Si le problème persiste veuillez contacter la CCR.")

    public ServiceExampleDtoInterface getPvseCompleteByKey(@Parameter(name = "unCodeContrat", description = "unCodeContrat", required = true) @QueryParam("unCodeContrat") String unCodeContrat, @Parameter(

            name = "unSocieteCCR", description = "unSocieteCCR", required = true) @QueryParam("unSocieteCCR") String unSocieteCCR, @Parameter(name = "unExerciceSouscription",

            description = "unExerciceSouscription", required = true) @QueryParam("unExerciceSouscription") int unExerciceSouscription, @Parameter(name = "unNumeroOrdre",

            description = "unNumeroOrdre", required = true) @QueryParam("unNumeroOrdre") String unNumeroOrdre,

            @Parameter(name = "unNumeroSection", description = "unNumeroSection", required = true) @QueryParam("unNumeroSection") String unNumeroSection, @Parameter(name = "unNumeroAvenant",

                    description = "unNumeroAvenant", required = true) @QueryParam("unNumeroAvenant") String unNumeroAvenant);
}