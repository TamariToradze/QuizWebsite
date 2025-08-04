const planets = document.querySelectorAll(".planet");
let startTime = Date.now(); // Startzeitpunkt der Animation

function updatePositions() {
  const now = Date.now() - startTime; // Aktuelle vergangene Zeit seit dem Start

  planets.forEach((planet, index) => {
    const orbitTime = (index + 1) * 5000; // Umlaufzeit in Millisekunden (5s für Merkur, 10s für Venus usw.)
    const distanceFromSun = (index + 1) * 50; // Abstand zur Sonne in Pixel (50 für Merkur, 100 für Venus usw.)
    const diameter = getPlanetDiameter(index + 1); // Durchmesser des Planeten basierend auf dem Index
    const angle = ((now % orbitTime) / orbitTime) * 360; // Aktueller Winkel basierend auf der vergangenen Zeit
    const x = distanceFromSun * Math.cos((angle * Math.PI) / 180); // X-Position basierend auf dem Abstand und dem Winkel
    const y = distanceFromSun * Math.sin((angle * Math.PI) / 180); // Y-Position basierend auf dem Abstand und dem Winkel

    planet.style.width = `${diameter}px`; // Setzen der Breite basierend auf dem Durchmesser
    planet.style.height = `${diameter}px`; // Setzen der Höhe basierend auf dem Durchmesser
    planet.style.left = `calc(50% - ${diameter / 2}px + ${x}px)`; // Anpassung der Positionsberechnung für den Durchmesser
    planet.style.top = `calc(50% - ${diameter / 2}px + ${y}px)`; // Anpassung der Positionsberechnung für den Durchmesser
  });
}

function getPlanetDiameter(index) {
  const diameters = [6, 15, 16, 8, 160, 130, 60, 55, 4]; // Durchmesser der Planeten in Kilometern
  const scaleFactor = [1, 0.7, 0.6, 0.4, 0.2, 0.1, 0.08, 0.05, 0.002]; // Skalierungsfaktor für die Größenrelation
  return diameters[index - 1] * scaleFactor[index - 1]; // Rückgabe des Durchmessers mit Skalierung
}

function restartAnimation() {
  startTime = Date.now(); // Setzen der Startzeitpunkt der Animation auf die aktuelle Zeit
}

function createStars() {
  const starField = document.querySelector(".star-field");
  const numStars = 100; // Anzahl der Sterne

  for (let i = 0; i < numStars; i++) {
    const star = document.createElement("div");
    star.classList.add("star");
    star.style.left = `${Math.random() * 100}%`; // Zufällige horizontale Position
    star.style.top = `${Math.random() * 100}%`; // Zufällige vertikale Position
    star.style.animationDelay = `${Math.random()}s`; // Zufällige Verzögerung für den Twinkle-Effekt
    starField.appendChild(star);
  }
}

// Event Listener für das Laden der Seite
window.addEventListener('load', () => {
  createStars();
  setInterval(updatePositions, 1000 / 60); // Alle 16,67 ms aktualisieren (ca. 60 FPS)
});
