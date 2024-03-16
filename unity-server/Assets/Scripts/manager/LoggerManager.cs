using com.tvd12.ezyfoxserver.client.logger;
using UnityEngine;

public class LoggerManager : MonoBehaviour
{
	private void Awake()
	{
		EzyLoggerFactory.setLoggerLevel(EzyLoggerLevel.DEBUG);
		EzyLoggerFactory.setLoggerSupply(type => new EzyUnityLogger(type));
	}
}
