$( document ).ready( function(){
	var scene = new THREE.Scene();
	var camera = new THREE.PerspectiveCamera( 75, window.innerWidth / window.innerHeight, 0.1, 1000 );
	var renderer = new THREE.WebGLRenderer({antialias: true, alpha: true});
	renderer.setSize( 500, 320);//window.innerWidth/3, window.innerHeight/3 );
	renderer.setClearColor( 0xffffff, 0);	// Together with "alpha:true" it makes the background transparent. This way the background color can be controlled through the CSS.
	
	// Creating the object
	var geometry = new THREE.BoxGeometry( 1, 1, 1 );
	var material = new THREE.MeshBasicMaterial( { color: 0x6B1F3C } );
	var cube = new THREE.Mesh( geometry, material );
	// Adding it to the scene and positioning the camera
	scene.add( cube );
	camera.position.z = 3;
	
	// Putting the view in the webpage:
	$(".modelViewer").append( renderer.domElement );
	
	/* Determining how the object will move in the view. */
	// Defining the flags adn variables that will determine the movements of the robot (and its background) in the view.
	var rightFlag=false, leftFlag=false, forwardFlag=false, backwardFlag=false, stopFlag=false;
	var backgroundPositionX = 0, backgroundPositionY = 0;
	var robotDegrees = 0;	// the degree at which the robot is bended towards, starting from upward. Range: [0, 360].
	var normZ;
	// Defining the animations (the operations that will be repeated 60 times per second).
	function render(){
		requestAnimationFrame( render );
		
		moveTheRobot();		// handles the movements of the robot in the browser.
		
		renderer.render( scene, camera ); // Defines which scene and camera to which we want to apply the modifications.
	}
	render();	// Starting the animation.
	
	// Function that moves the robot in the 3D-web-view (if necessary).
	function moveTheRobot(){
		if(stopFlag == false){	// if the robot is moving...
			if(rightFlag == true){ cube.rotation.z -= 0.01; }
			if(leftFlag == true){ cube.rotation.z += 0.01; }
			if(forwardFlag == true){
				// At the beginning the robot faces up. (90 degrees for the cartesian view)
				// Its z indez starts as 0. To move right we subtrack an amount from it. To move left we add.
				// The robot does a 360-turn with a movement of 6.28 (and multiples).
				// So we have to normalize the Z value in the [-6.28, 6.28] range and then convert it to the cartesian view in order to have its direction.
				normZ = cube.rotation.z % 6.28;		// Its sign (specifying a left-turn or a right-turn) will remain the same.
				console.log("----------------------------------------");
				console.log("original z: "+cube.rotation.z+"   normZ: "+normZ);
				
				robotDegrees = calculateDirection(normZ);
				
				console.log("cos:  "+Math.cos(Math.PI * (robotDegrees/180))+"    Inverted sin: "+(-Math.sin(Math.PI * (robotDegrees/180))));
				// Knowing the degree we can now add a value up to 1 (for each cycle) to the background position X and Y using the Math.sin and Math.cos functions.
				backgroundPositionX += Math.cos(Math.PI * (robotDegrees/180))*2;	// *2 is applied to make it move faster.
				backgroundPositionY += (-Math.sin(Math.PI * (robotDegrees/180))*2);	// (Math.PI * (robotDegrees/180)) is applied because the function Math.sin and Math.cos need a radiant in input.
				// Finally, we can move the background
				$('.modelViewer canvas').css('background-position', backgroundPositionX +'px '+ backgroundPositionY +'px');
			}
			if(backwardFlag == true){	// Like for the 'forward' condition, but in reverse.
				normZ = cube.rotation.z % 6.28;		// Its sign (specifying a left-turn or a right-turn) will remain the same.
				robotDegrees = calculateDirection(normZ);
				// Knowing the degree we can now subtrack a value up to 1 (for each cycle) to the background position X and Y using the Math.sin and Math.cos functions.
				backgroundPositionX -= Math.cos(Math.PI * (robotDegrees/180))*2;	// *2 is applied to make it move faster.
				backgroundPositionY -= (-Math.sin(Math.PI * (robotDegrees/180))*2);	// (Math.PI * (robotDegrees/180)) is applied because the function Math.sin and Math.cos need a radiant in input.
				// Finally, we can move the background
				$('.modelViewer canvas').css('background-position', backgroundPositionX +'px '+ backgroundPositionY +'px');
			}
		}
	}
	// Defining the moving operations
	function moveRight(){
		stopFlag = false;	// we are moving
		leftFlag = false;
		forwardFlag = false;
		backwardFlag = false;
		rightFlag = true;	// we are only moving to the right
	}
	function moveLeft(){
		stopFlag = false;	// we are moving
		rightFlag = false;
		forwardFlag = false;
		backwardFlag = false;
		leftFlag = true;
	}
	function moveForward(){
		stopFlag = false;	// we are moving
		rightFlag = false;
		leftFlag = false;
		backwardFlag = false;
		forwardFlag = true;
	}
	function moveBackward(){
		stopFlag = false;	// we are moving
		rightFlag = false;
		leftFlag = false;
		forwardFlag = false;
		backwardFlag=true;
	}
	function stopRobot(){ stopFlag=true; }
	
	// This function will calculate the direction and verse at which the background has to move (in order to make the robot look like moving in the opposite verse).
	function calculateDirection(normZ){
		// From the equation:   cube.rotation.z / 6.28 = robotDegrees / 360    we get the degree from 0 to 360.
		if( normZ >= 0){
			robotDegrees = 90 + ((normZ / 6.28) * 360);
			robotDegrees = robotDegrees%360;
		}else{
			robotDegrees = 90 - (Math.abs(normZ / 6.28) * 360);
			// Now, if the resulting degree is negative it means it was more than 90 (from the upward direction). Since we want only positive degrees [0-360]
			// we have to normalize it (%360, because it could be even -2000 if the robot has spun a lot) and subtract it from 360.
			if(robotDegrees < 0){
				robotDegrees = 360 - (Math.abs(robotDegrees%360));
			}
		}
		console.log("robot's degree: "+robotDegrees);
		// Now we want to add 180 degrees to make the background move towards the back of the robot.
		robotDegrees = (robotDegrees+180)%360;
		console.log("robot back's degree: "+robotDegrees);
		
		return robotDegrees;	// this is the degree at which the background has to move toward.
	}
	
	// This function will enable or disable the autonomous mode
	function autonomousMode(){
		var title = $("input.autonomousModeArea").val();
		
		if(title === "Autonomous mode"){	// We are activating the autonomous mode
			//$("input.onoffswitch-checkbox").trigger('click'); can be used to trigger the switch that determines the mode ("BACK", "FORW")
			$("input.autonomousModeArea").val("Back to manual");	 // Giving a feedback and a mean to switch back to the User through the button just clicked.
		}else{
			$("input.autonomousModeArea").val("Autonomous mode");  // Giving a feedback and a mean to switch back to the User through the button just clicked.
		}
	}
	
	function autonomousModeDirectionChange(){
		$("input.onoffswitch-checkbox").trigger('click');
	}
	
	// Assigning the operations to the buttons in the GUI.
	$(".buttonsArea #rightButton").click(moveRight);
	$(".buttonsArea #leftButton").click(moveLeft);
	$(".buttonsArea #forwardButton").click(moveForward);
	$(".buttonsArea #backwardButton").click(moveBackward);
	$(".buttonsArea #stopButton").click(stopRobot);
	$("input.onoffswitch-checkbox").click(autonomousModeDirectionChange);
	$("input.autonomousModeArea").click(autonomousMode);
});