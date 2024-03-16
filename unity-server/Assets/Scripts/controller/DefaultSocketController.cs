using com.tvd12.ezyfoxserver.client.entity;
using com.tvd12.ezyfoxserver.client.factory;
using com.tvd12.ezyfoxserver.client.request;
using com.tvd12.ezyfoxserver.client.support;
using com.tvd12.ezyfoxserver.client.unity;
using UnityEngine;
using Object = System.Object;

public class DefaultSocketController : EzyAbstractController
{
	[SerializeField]
	private GameObject square;
	
	protected override EzySocketConfig GetSocketConfig()
	{
		return EzySocketConfig.GetBuilder()
			.ZoneName(Constants.ZONE_NAME)
			.AppName(Constants.APP_NAME)
			.TcpUrl(Constants.TCP_URL)
			.UdpPort(Constants.UDP_PORT)
			.UdpUsage(Constants.UDP_USAGE)
			.EnableSSL(Constants.ENABLE_SSL)
			.Build();
	}

	private void Start()
	{
		AddHandler<EzyObject>(Commands.MOVE_TRANSFORM, HandleMoveTransform);
		
		var socketConfig = GetSocketConfig();
		socketProxy.setLoginUsername("unityserver");
		socketProxy.setLoginPassword("password");
		
		socketProxy.setUrl(socketConfig.TcpUrl);
		socketProxy.setUdpPort(socketConfig.UdpPort);
		socketProxy.setDefaultAppName(socketConfig.AppName);
		OnUdpHandshake<Object>(OnUdpHandshakeResponse);

		socketProxy.connect();
	}
	
	private void HandleMoveTransform(EzyAppProxy appproxy, EzyObject data)
	{
		float offsetX = data.get<float>("offsetX");
		float offsetY = data.get<float>("offsetY");
		float offsetZ = data.get<float>("offsetZ");

		square.transform.position += new Vector3(offsetX, offsetY, offsetZ);

		EzyObject result = EzyEntityFactory.newObjectBuilder()
			.append("x", square.transform.position.x)
			.append("y", square.transform.position.y)
			.append("z", square.transform.position.z)
			.build();
		appproxy.send(Commands.MOVE_TRANSFORM_RESULT, result);
	}

	private void OnUdpHandshakeResponse(EzySocketProxy proxy, Object data)
	{
		LOGGER.debug("Udp handshake successfully");
		socketProxy.send(new EzyAppAccessRequest(GetSocketConfig().AppName));
	}
}
