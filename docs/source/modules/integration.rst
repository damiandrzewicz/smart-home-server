Integration Module
------------------

The integration module's responsibility is managing connections with system `nodes`. Every `node` is single unit based on the template which can be expanded to suit system's specific requrements.

Request-Response design
#######################

Base messages
#############

Eeach `node template` provides following common messages:
    - login
    - health check
    - info
    - query tasks

Login Message
^^^^^^^^^^^^^

- Request

    .. code-block:: json

        {
            "messageName":"loginRequest",
            "content":
            {
                "login":"mylogin123",
                "password":"mypassword123"
            }
        }

- Response

    - Ok

    .. code-block:: json

        {
            "messageName":"loginResponse",
            "result":
            {
                "state":"ok"
            }
        }

    - Error

    .. code-block:: json

        {
            "messageName":"loginResponse",
            "result":
            {
                "state":"error",
                "message":"wrong credentials"
            }
        }        

DeviceInfo Message
^^^^^^^^^^^^^

- Request

    .. code-block:: json

        {
            "messageName":"systemInfoRequest",
            "content":
            {
                moduleInfo:
                {
                    "moduleType":"watherStation"
                }
                espInfo:
                {
                    "espType":"esp32",
                    "sdkVersion":"1.2.1"
                }
                memoryInfo:
                {
                    "heapSize":12345,
                    "minHeapSize":345
                }
            }
        }

- Response

    - Ok

    .. code-block:: json

        {
            "messageName":"loginResponse",
            "result":
            {
                "state":"ok"
            }
        }

    - Error

    .. code-block:: json

        {
            "messageName":"loginResponse",
            "result":
            {
                "state":"error",
                "message":"error message"
            }
        }        



blabla...

.. uml:: /uml/domain/node.uml

Some title
^^^^^^^^^^