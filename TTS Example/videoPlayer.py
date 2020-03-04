import pyglet
import cv2
vidPath = 'video.mp4'
# window = pyglet.window.Window()
# player = pyglet.media.Player()
# source = pyglet.media.StreamingSource()
# MediaLoad = pyglet.media.load(vidPath)
#
# player.queue(MediaLoad)
# player.play()
#
#
# @window.event
# def on_draw():
#     window.clear()
#     if player.source and player.source.video_format:
#         player.get_texture().blit(50, 50)
#
#
# pyglet.app.run()

cap = cv2.VideoCapture(vidPath)
ret, frame = cap.read()
while 1:
    ret, frame = cap.read()
    cv2.imshow("frame",frame)
    if cv2.waitKey(24) & 0xFF == ord('q') or ret == False:
        cap.release()
        cv2.destroyAllWindows()
        break
    cv2.imshow("frame", frame)
