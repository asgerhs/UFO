from PIL import Image

bits = []
with Image.open("ufoopgave1.png") as img:
    width, height = img.size
    for x in range(0, height):
        for y in range(0, width):
            pixel = list(img.getpixel((y, x)))
           
            bits.append(str(pixel[2]&1))
split = ["".join(bits[i:i+8][::-1]) for i in range(0, len(bits), 8)]
result = ""
for b in split:
    if b != "00000000":
        result += chr(int(b, 2))
    else:
        break
print(result)
