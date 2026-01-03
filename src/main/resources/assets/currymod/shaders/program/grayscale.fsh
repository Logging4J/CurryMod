#version 150

uniform sampler2D DiffuseSampler;
uniform float Intensity; // 0.0 = colorido, 1.0 = preto e branco

in vec2 texCoord;
out vec4 fragColor;

void main() {
    vec4 color = texture(DiffuseSampler, texCoord);

    // Fórmula de luminância (padrão ITU-R BT.709)
    float gray = dot(color.rgb, vec3(0.299, 0.587, 0.114));

    // Mistura entre cor original e grayscale baseado na intensidade
    vec3 finalColor = mix(color.rgb, vec3(gray), Intensity);

    fragColor = vec4(finalColor, color.a);
}