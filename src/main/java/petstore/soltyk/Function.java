package petstore.soltyk;

import java.util.Optional;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.OutputBinding;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.BlobOutput;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import com.microsoft.azure.functions.annotation.StorageAccount;

public class Function {
    @FunctionName("resolveOrder")
    @StorageAccount("AZURE_STORAGE")
    public void run(
            @HttpTrigger(
                    name = "req",
                    methods = {HttpMethod.POST},
                    authLevel = AuthorizationLevel.ANONYMOUS)
                    HttpRequestMessage<Optional<String>> request,
            @BlobOutput(
                    name = "output",
                    path = "orders/{Query.filename}.json")
                    OutputBinding<String> outputItem,
            final ExecutionContext context) {

        context.getLogger().info("Uploading file" + request.getQueryParameters().get("filename") + ".json");

        if (request.getBody().isPresent()) {
            outputItem.setValue(request.getBody().get());
        }
    }
}
