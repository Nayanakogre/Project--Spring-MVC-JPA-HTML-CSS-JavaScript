<html>
<body>
<style>
    footer {
            background-color: LightSeaGreen;
            color: white;
            padding: 10px 0;
            position: fixed;
            width: 100%;
            bottom: 0;
        }
</style>
<footer class="text-center">
    <p>&copy; <span id="footerYear"></span> Nayana K J | <span id="footerDatetime"></span></p>
</footer>

<script>
    function updateDateTime() {
        const now = new Date();
        document.getElementById("footerDatetime").textContent = now.toLocaleString();
        document.getElementById("footerYear").textContent = now.getFullYear();
    }
    setInterval(updateDateTime, 1000);
    updateDateTime();
</script>
</body>
</html>

