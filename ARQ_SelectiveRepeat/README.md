# Selective Repeat ARQ

The program is written in python and implements the Selective Repeat of ARQ error-control method. With selective repeat, the sender sends a number of frames specified by a window size even without the need to wait for individual ACK from the receiver as in Go-Back-N ARQ. The receiver may selectively reject a single frame, which may be retransmitted alone; this contrasts with other forms of ARQ, which must send every frame from that point again. The receiver accepts out-of-order frames and buffers them. The sender individually retransmits frames that have timed out.

The server and client side of program should be runned with following options:


--i, --ip                   specify destination ip address

-t, --timeout               specify timeout between windows

-pc, --client_port          specify the client port

-ps, --server_port          specify the server port

-f, --file                  specify the transieved file
