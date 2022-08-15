package scaffolding;

import java.awt.Image;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Foot {
	Image image;
	int x;
	int y;
	int width;
	int height;
}
