#version 330 compatibility

uniform sampler2D tex;
uniform sampler2D target;

in vec2 tex_coord;

void main(){
	vec4 light = texture2D(tex, tex_coord);
	vec4 color = light + texture2D(target, tex_coord);
	gl_FragColor = color / (light + 1.0);
}