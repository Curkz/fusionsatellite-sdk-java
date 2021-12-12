package au.com.dmg.fusion.conversion;

import org.json.JSONObject;

import au.com.dmg.fusion.Message;

public class VersionSwitch {

    public Message Convert(String incomingMessageStr, String myVersionStr) {

        JSONObject incomingMessage = new JSONObject(incomingMessageStr);
        String theirVersionStr = incomingMessage.get("FusionSatelliteLibraryVersion");

        // Get Integer version
        Integer myVersionInt = Integer.parseInt(myVersionStr.replaceAll("[^0-9]", ""));
        Integer theirVersionInt = Integer.parseInt(theirVersionStr.replaceAll("[^0-9]", ""));


        if (this.IsConversionUp(myVersionInt, theirVersionInt)) {
            // Convert incomingMessage up one version
            this.ConvertUp(incomingMessage, myVersionInt);
        } else {
            // Convert incomingMessage down one version
            this.ConvertDown(incomingMessage, myVersionInt);
        }
    }

    private JSONObject ConvertUp(JSONbject incomingMessage, Integer myVersionInt) {

        String theirVersionStr = incomingMessage.get("FusionSatelliteLibraryVersion");
        Integer theirVersionInt = Integer.parseInt(theirVersionStr.replaceAll("[^0-9]", ""));
        
        if (theirVersionInt == myVersionInt) {
            return incomingMessage;

        } else if (theirVersionInt == 120) {
            // Convert incomingMessage to 121
            
            // Do conversion here and alter incomingMessage
            

            return this.ConvertUp(incomingMessage, myVersionInt);
        }
    }

    private JSONObject ConvertDown(JSONObject incomingMessage, Integer myVersionInt) {

        String theirVersionStr = incomingMessage.get("FusionSatelliteLibraryVersion");
        Integer theirVersionInt = Integer.parseInt(theirVersionStr.replaceAll("[^0-9]", ""));
        
        if (theirVersionInt == myVersionInt) {
            return incomingMessage;
            
        } else if (theirVersionInt == 121) {
            // Convert incomingMessage to 120

            // Do conversion here and alter incomingMessage

            return this.ConvertDown(incomingMessage, myVersionInt);
        }
    }




    private Boolean IsConversionUp(Integer myVersionInt, Integer theirVersionInt) {
        // True if theirVersion is lower than mine -> need to convert incomingMessage upwards
        return (theirVersionInt < myVersionInt);
    }
}
