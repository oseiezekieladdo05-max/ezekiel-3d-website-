// ===== BASIC SETUP =====
const scene = new THREE.Scene();

const camera = new THREE.PerspectiveCamera(
  75,
  window.innerWidth / window.innerHeight,
  0.1,
  1000
);

const renderer = new THREE.WebGLRenderer({
  canvas: document.getElementById("scene"),
  alpha: true,
  antialias: true
});

renderer.setSize(window.innerWidth, window.innerHeight);
document.body.appendChild(renderer.domElement);

// ===== LIGHTING =====
const light = new THREE.DirectionalLight(0xffffff, 1);
light.position.set(5, 5, 5);
scene.add(light);

const ambient = new THREE.AmbientLight(0x404040, 2);
scene.add(ambient);

// ===== COMPUTER (SIMPLIFIED 3D MODEL) =====

// Screen
const screenGeometry = new THREE.BoxGeometry(3, 2, 0.2);
const screenMaterial = new THREE.MeshStandardMaterial({
  color: 0x111111,
});
const screen = new THREE.Mesh(screenGeometry, screenMaterial);
screen.position.y = 1;
scene.add(screen);

// Keyboard
const keyboardGeometry = new THREE.BoxGeometry(3, 0.2, 1.5);
const keyboardMaterial = new THREE.MeshStandardMaterial({
  color: 0x333333,
});
const keyboard = new THREE.Mesh(keyboardGeometry, keyboardMaterial);
keyboard.position.y = -1;
scene.add(keyboard);

// Base stand
const baseGeometry = new THREE.BoxGeometry(0.5, 1, 0.5);
const baseMaterial = new THREE.MeshStandardMaterial({
  color: 0x222222,
});
const base = new THREE.Mesh(baseGeometry, baseMaterial);
base.position.y = 0;
scene.add(base);

// ===== CAMERA POSITION =====
camera.position.z = 6;

// ===== PARALLAX SCROLL EFFECT =====
let scrollY = 0;

window.addEventListener("scroll", () => {
  scrollY = window.scrollY;
});

// Smooth animation loop
function animate() {
  requestAnimationFrame(animate);

  // Parallax movement
  screen.rotation.y = scrollY * 0.0005;
  keyboard.rotation.y = scrollY * 0.0005;

  camera.position.y = -scrollY * 0.002;

  // subtle floating animation
  screen.position.x = Math.sin(Date.now() * 0.001) * 0.1;
  keyboard.position.x = Math.sin(Date.now() * 0.001) * 0.1;

  renderer.render(scene, camera);
}

animate();

// ===== RESPONSIVE =====
window.addEventListener("resize", () => {
  camera.aspect = window.innerWidth / window.innerHeight;
  camera.updateProjectionMatrix();
  renderer.setSize(window.innerWidth, window.innerHeight);
});
