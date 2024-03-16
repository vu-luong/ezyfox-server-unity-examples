using com.tvd12.ezyfoxserver.client.unity;

public class SocketEventLoopManager : EzyAbstractEventProcessor
{
	protected override string GetZoneName()
	{
		return Constants.ZONE_NAME;
	}
}
