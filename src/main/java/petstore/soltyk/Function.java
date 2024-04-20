package petstore.soltyk;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.OutputBinding;
import com.microsoft.azure.functions.annotation.BlobOutput;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.ServiceBusQueueTrigger;
import com.microsoft.azure.functions.annotation.StorageAccount;

public class Function {
    @FunctionName("resolveOrder")
    @StorageAccount("AZURE_STORAGE")
    public void run(
            @ServiceBusQueueTrigger(
                    name = "msg",
                    queueName = "%SB_QUEUE_NAME%",
                    connection = "%SB_CONNECTION_STRING%")
                    String message,
            @BlobOutput(
                    name = "output",
                    path = "orders/{id}.json")
                    OutputBinding<String> outputItem,
            final ExecutionContext context) {

            outputItem.setValue(message);

    }
}
