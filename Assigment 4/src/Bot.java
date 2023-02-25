import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.messages.*;
import com.vk.api.sdk.queries.messages.MessagesGetLongPollHistoryQuery;
import org.postgresql.osgi.PGDataSourceFactory;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Bot {
    public static void main(String[] args) throws ClientException, ApiException, InterruptedException {
        JFrame parent = new JFrame();
        Interface dialog = new Interface(parent);

        String username = dialog.getTxlogin();
        String password = dialog.getTxpassword();

        Autentification aut = new Autentification(username, password, username);
        TransportClient transportClient = new HttpTransportClient();
        VkApiClient vk = new VkApiClient(transportClient);
        Random random = new Random();
        Keyboard keyboard = new Keyboard();
        List<List<KeyboardButton>> allKey = new ArrayList<>();
        List<KeyboardButton> line1 = new ArrayList<>();
        line1.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Привет").setType(KeyboardButtonActionType.TEXT)).setColor(KeyboardButtonColor.POSITIVE));
        line1.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Кто я?").setType(KeyboardButtonActionType.TEXT)).setColor(KeyboardButtonColor.POSITIVE));
        allKey.add(line1);

        GroupActor actor = new GroupActor(219000445, "vk1.a.wxsHMrUoVNcen39FY_4iAFESxdZJSBD7Szg8pCWpmwL9vsKyESrnC1P4eLzOkmgtx-mKrDum2Erfu3pgduIa8wv1rjaheCWS--WxZ2PJy_V_BvcNHzncibtnxcvZtrBfibZbs5GWfbgGnOUzmGQJmDmDViAj3eZn_VkUhwcPU9o9t5ic0Kfg5Tg6RN-zp62tZe7N_lQg0DK_JRNZVE8DPQ");
        Integer ts = vk.messages().getLongPollServer(actor).execute().getTs();
        MessagesGetLongPollHistoryQuery historyQuery = vk.messages().getLongPollHistory(actor).ts(ts);
        List<Message> messages = historyQuery.execute().getMessages().getItems();
        if (dialog.isSucceeded()) {
            vk.messages().send(actor).message("User is authorized").userId(785935374).randomId(random.nextInt(10000)).execute();
        }
        if (!dialog.isSucceeded()) {
            vk.messages().send(actor).message("User is not authorized").userId(785935374).randomId(random.nextInt(10000)).execute();
        }
            messages.forEach(message -> {
                System.out.println(message.toString());
                try {
                    if (dialog.isSucceeded()) {
                        vk.messages().send(actor).message("Temp").userId(785935374).randomId(random.nextInt(10000)).execute();
                    }
                    if (!dialog.isSucceeded()) {
                        vk.messages().send(actor).message("Temp").userId(785935374).randomId(random.nextInt(10000)).execute();
                    } else {
                        vk.messages().send(actor).message("User is not found").userId(message.getFromId()).randomId(random.nextInt(10000)).execute();
                    }
                } catch (ApiException | ClientException e) {
                    e.printStackTrace();

                }
            });
        }
    }
