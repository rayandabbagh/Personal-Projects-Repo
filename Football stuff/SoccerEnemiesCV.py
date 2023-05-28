import cv2
import numpy as np

# Set up video capture
cap = cv2.VideoCapture("Soccer.mp4")

# Define color range for detecting the ball (in this case, orange)
lower_orange = np.array([0, 50, 100])
upper_orange = np.array([20, 255, 255])

# Define color range for detecting the opposing team (in this case, blue)
lower_blue = np.array([100, 50, 50])
upper_blue = np.array([140, 255, 255])

while True:
    # Capture a frame from the video
    ret, frame = cap.read()

    # Convert the frame to HSV color space
    hsv = cv2.cvtColor(frame, cv2.COLOR_BGR2HSV)

    # Threshold the frame to get only the orange parts (i.e. the ball)
    ball_mask = cv2.inRange(hsv, lower_orange, upper_orange)

    # Threshold the frame to get only the blue parts (i.e. the opposing team)
    opposing_mask = cv2.inRange(hsv, lower_blue, upper_blue)

    # Find contours in the masked images
    ball_contours, hierarchy = cv2.findContours(ball_mask, cv2.RETR_TREE, cv2.CHAIN_APPROX_SIMPLE)
    opposing_contours, hierarchy = cv2.findContours(opposing_mask, cv2.RETR_TREE, cv2.CHAIN_APPROX_SIMPLE)

    # If there are contours for the ball (i.e. the ball is detected)
    if len(ball_contours) > 0:
        # Find the contour with the largest area (i.e. the ball)
        max_contour = max(ball_contours, key=cv2.contourArea)

        # Get the position of the ball (i.e. the center of the contour)
        ball_x, ball_y, ball_w, ball_h = cv2.boundingRect(max_contour)
        ball_center_x = ball_x + ball_w // 2
        ball_center_y = ball_y + ball_h // 2

        # Draw a circle around the ball
        cv2.circle(frame, (ball_center_x, ball_center_y), 10, (0, 0, 255), 2)

    # If there are contours for the opposing team
    if len(opposing_contours) > 0:
        # Draw a circle around each opposing player
        for opposing_contour in opposing_contours:
            # Get the position of the opposing player (i.e. the center of the contour)
            opposing_x, opposing_y, opposing_w, opposing_h = cv2.boundingRect(opposing_contour)
            opposing_center_x = opposing_x + opposing_w // 2
            opposing_center_y = opposing_y + opposing_h // 2

            # Draw a circle around the opposing player
            cv2.circle(frame, (opposing_center_x, opposing_center_y), 10, (255, 0, 0), 2)

    # Display the resulting image
    cv2.imshow('frame', frame)

    # Exit if the 'q' key is pressed
    if cv2.waitKey(1) & 0xFF == ord('q'):
        break

# Release the capture and close the window
cap.release()
cv2.destroyAllWindows()
