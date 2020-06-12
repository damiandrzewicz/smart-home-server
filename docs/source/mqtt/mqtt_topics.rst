.. ................
.. Base Mqtt topics
.. ................

.. |mqtt_root| replace:: myhome
.. |mqtt_node| replace:: node 
.. |mqtt_srv| replace:: server 
.. |mqtt_client_id| replace:: {client_id}

.. ...................
.. Base Mqtt subdomain
.. ...................

.. |mqtt_base| replace:: base

.. ...................
.. Base Mqtt operations
.. ...................

.. |mqtt_auth| replace:: auth
.. |mqtt_info| replace:: info
.. |mqtt_health| replace:: health
.. |mqtt_get| replace:: getOperations
.. |mqtt_put| replace:: putData

.. .........
.. Node Auth
.. .........

.. |mqtt_node_auth_pub| replace:: |mqtt_root|/|mqtt_srv|/|mqtt_base|/|mqtt_auth|/|mqtt_client_id|
.. |mqtt_srv_auth_sub| replace:: |mqtt_root|/|mqtt_srv|/|mqtt_base|/|mqtt_auth|/+
.. |mqtt_srv_auth_pub| replace:: |mqtt_root|/|mqtt_node|/|mqtt_base|/|mqtt_auth|/|mqtt_client_id|
.. |mqtt_node_auth_sub| replace:: |mqtt_root|/|mqtt_node|/|mqtt_base|/|mqtt_auth|/|mqtt_client_id|

.. ...........
.. Node Info
.. ...........

.. |mqtt_node_info_pub| replace:: |mqtt_root|/|mqtt_srv|/|mqtt_base|/|mqtt_info|/|mqtt_client_id|
.. |mqtt_srv_info_sub| replace:: |mqtt_root|/|mqtt_srv|/|mqtt_base|/|mqtt_info|/+
.. |mqtt_srv_info_pub| replace:: |mqtt_root|/|mqtt_node|/|mqtt_base|/|mqtt_info|/|mqtt_client_id|
.. |mqtt_node_info_sub| replace:: |mqtt_root|/|mqtt_node|/|mqtt_base|/|mqtt_info|/|mqtt_client_id|

.. .................
.. Node Health Check
.. .................

.. |mqtt_node_health_pub| replace:: |mqtt_root|/|mqtt_srv|/|mqtt_base|/|mqtt_health|/|mqtt_client_id|
.. |mqtt_srv_health_sub| replace:: |mqtt_root|/|mqtt_srv|/|mqtt_base|/|mqtt_health|/+
.. |mqtt_srv_health_pub| replace:: |mqtt_root|/|mqtt_node|/|mqtt_base|/|mqtt_health|/|mqtt_client_id|
.. |mqtt_node_health_sub| replace:: |mqtt_root|/|mqtt_node|/|mqtt_base|/|mqtt_health|/|mqtt_client_id|

.. .................
.. Node Get
.. .................

.. |mqtt_node_get_pub| replace:: |mqtt_root|/|mqtt_srv|/|mqtt_base|/|mqtt_get|/|mqtt_client_id|
.. |mqtt_srv_get_sub| replace:: |mqtt_root|/|mqtt_srv|/|mqtt_base|/|mqtt_get|/+
.. |mqtt_srv_get_pub| replace:: |mqtt_root|/|mqtt_node|/|mqtt_base|/|mqtt_get|/|mqtt_client_id|
.. |mqtt_node_get_sub| replace:: |mqtt_root|/|mqtt_node|/|mqtt_base|/|mqtt_get|/|mqtt_client_id|

.. .................
.. Node Put
.. .................

.. |mqtt_node_put_pub| replace:: |mqtt_root|/|mqtt_srv|/|mqtt_base|/|mqtt_put|/|mqtt_client_id|
.. |mqtt_srv_put_sub| replace:: |mqtt_root|/|mqtt_srv|/|mqtt_base|/|mqtt_put|/+
.. |mqtt_srv_put_pub| replace:: |mqtt_root|/|mqtt_node|/|mqtt_base|/|mqtt_put|/|mqtt_client_id|
.. |mqtt_node_put_sub| replace:: |mqtt_root|/|mqtt_node|/|mqtt_base|/|mqtt_put|/|mqtt_client_id|
