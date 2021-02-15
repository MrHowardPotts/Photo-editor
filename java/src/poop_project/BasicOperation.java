package poop_project;

class Fill extends Operation {

	public Fill(Pixel pixx) {
		super("Fill");
//		changePixel(new Pixel(r, g, b, a));
		pix = pixx;
	}

	@Override
	public void work(Image image) {
		if (image.selections.size() == 0) {

			for (Layer lay : image.layers) {
				for (int i = 0; i < lay.height; i++) {
					for (int j = 0; j < lay.width; j++) {

						lay.pixels.get(i).set(j, pix);

					}
				}
			}

		} else {
			for (Layer lay : image.layers) {
				for (Selection sale : image.selections) {
					if (!sale.active)
						continue;
					for (int i = sale.y; i < sale.height + sale.y; i++) {
						for (int j = sale.x; j < sale.width + sale.x; j++) {

							lay.pixels.get(i).set(j, pix);

						}
					}
				}
			}
		}
		image.revalidate();
		image.repaint();
	}
}

//--------------------------------------------------------------------------------------
class Add extends Operation {

	public Add(double val) {
		super("Add");
//		changePixel(new Pixel(r, g, b, a));
		value = val;
	}

	@Override
	public void work(Image image) {
		if (image.selections.size() == 0) {

			for (Layer lay : image.layers) {
				for (int i = 0; i < lay.height; i++) {
					for (int j = 0; j < lay.width; j++) {
						int r = lay.pixels.get(i).get(j).getRed();
						int g = lay.pixels.get(i).get(j).getGreen();
						int b = lay.pixels.get(i).get(j).getBlue();
						int a = lay.pixels.get(i).get(j).getAlpha();

						lay.pixels.get(i).set(j,
								new Pixel((int) (r + value), (int) (g + value), (int) (b + value), (int) a));

					}
				}
			}

		} else {
			for (Layer lay : image.layers) {
				for (Selection sale : image.selections) {
					if (!sale.active)
						continue;
					for (int i = sale.y; i < sale.height + sale.y; i++) {
						for (int j = sale.x; j < sale.width + sale.x; j++) {

							int r = lay.pixels.get(i).get(j).getRed();
							int g = lay.pixels.get(i).get(j).getGreen();
							int b = lay.pixels.get(i).get(j).getBlue();
							int a = lay.pixels.get(i).get(j).getAlpha();

							lay.pixels.get(i).set(j,
									new Pixel((int) (r + value), (int) (g + value), (int) (b + value), (int) a));

						}
					}
				}
			}
		}
		image.revalidate();
		image.repaint();
	}
}

//--------------------------------------------------------------------------------------
class Sub extends Operation {

	public Sub(double val) {
		super("Sub");
//		changePixel(new Pixel(r, g, b, a));
		value = val;
	}

	@Override
	public void work(Image image) {
		if (image.selections.size() == 0) {

			for (Layer lay : image.layers) {
				for (int i = 0; i < lay.height; i++) {
					for (int j = 0; j < lay.width; j++) {
						int r = lay.pixels.get(i).get(j).getRed();
						int g = lay.pixels.get(i).get(j).getGreen();
						int b = lay.pixels.get(i).get(j).getBlue();
						int a = lay.pixels.get(i).get(j).getAlpha();

						lay.pixels.get(i).set(j,
								new Pixel((int) (r - value), (int) (g - value), (int) (b - value), (int) a));

					}
				}
			}

		} else {
			for (Layer lay : image.layers) {
				for (Selection sale : image.selections) {
					if (!sale.active)
						continue;
					for (int i = sale.y; i < sale.height + sale.y; i++) {
						for (int j = sale.x; j < sale.width + sale.x; j++) {

							int r = lay.pixels.get(i).get(j).getRed();
							int g = lay.pixels.get(i).get(j).getGreen();
							int b = lay.pixels.get(i).get(j).getBlue();
							int a = lay.pixels.get(i).get(j).getAlpha();

							lay.pixels.get(i).set(j,
									new Pixel((int) (r - value), (int) (g - value), (int) (b - value), (int) a));

						}
					}
				}
			}
		}
		image.revalidate();
		image.repaint();
	}
}

//--------------------------------------------------------------------------------------
class SubInvert extends Operation {

	public SubInvert(double val) {
		super("SubInvert");
//		changePixel(new Pixel(r, g, b, a));
		value = val;
	}

	@Override
	public void work(Image image) {
		if (image.selections.size() == 0) {

			for (Layer lay : image.layers) {
				for (int i = 0; i < lay.height; i++) {
					for (int j = 0; j < lay.width; j++) {
						int r = lay.pixels.get(i).get(j).getRed();
						int g = lay.pixels.get(i).get(j).getGreen();
						int b = lay.pixels.get(i).get(j).getBlue();
						int a = lay.pixels.get(i).get(j).getAlpha();

						lay.pixels.get(i).set(j,
								new Pixel((int) (value - r), (int) (value - g), (int) (value - b), (int) a));

					}
				}
			}

		} else {
			for (Layer lay : image.layers) {
				for (Selection sale : image.selections) {
					if (!sale.active)
						continue;
					for (int i = sale.y; i < sale.height + sale.y; i++) {
						for (int j = sale.x; j < sale.width + sale.x; j++) {

							int r = lay.pixels.get(i).get(j).getRed();
							int g = lay.pixels.get(i).get(j).getGreen();
							int b = lay.pixels.get(i).get(j).getBlue();
							int a = lay.pixels.get(i).get(j).getAlpha();

							lay.pixels.get(i).set(j,
									new Pixel((int) (value - r), (int) (value - g), (int) (value - b), (int) a));

						}
					}
				}
			}
		}
		image.revalidate();
		image.repaint();
	}
}

//--------------------------------------------------------------------------------------
class Mul extends Operation {

	public Mul(double val) {
		super("Mul");
//		changePixel(new Pixel(r, g, b, a));
		value = val;
	}

	@Override
	public void work(Image image) {
		if (image.selections.size() == 0) {

			for (Layer lay : image.layers) {
				for (int i = 0; i < lay.height; i++) {
					for (int j = 0; j < lay.width; j++) {
						int r = lay.pixels.get(i).get(j).getRed();
						int g = lay.pixels.get(i).get(j).getGreen();
						int b = lay.pixels.get(i).get(j).getBlue();
						int a = lay.pixels.get(i).get(j).getAlpha();

						lay.pixels.get(i).set(j,
								new Pixel((int) (value * r), (int) (value * g), (int) (value * b), (int) a));

					}
				}
			}

		} else {
			for (Layer lay : image.layers) {
				for (Selection sale : image.selections) {
					if (!sale.active)
						continue;
					for (int i = sale.y; i < sale.height + sale.y; i++) {
						for (int j = sale.x; j < sale.width + sale.x; j++) {

							int r = lay.pixels.get(i).get(j).getRed();
							int g = lay.pixels.get(i).get(j).getGreen();
							int b = lay.pixels.get(i).get(j).getBlue();
							int a = lay.pixels.get(i).get(j).getAlpha();

							lay.pixels.get(i).set(j,
									new Pixel((int) (value * r), (int) (value * g), (int) (value * b), (int) a));

						}
					}
				}
			}
		}
		image.revalidate();
		image.repaint();
	}
}

//--------------------------------------------------------------------------------------
class Div extends Operation {

	public Div(double val) {
		super("Div");
//		changePixel(new Pixel(r, g, b, a));
		value = val;
	}

	@Override
	public void work(Image image) {
		if (image.selections.size() == 0) {

			for (Layer lay : image.layers) {
				for (int i = 0; i < lay.height; i++) {
					for (int j = 0; j < lay.width; j++) {
						int r = lay.pixels.get(i).get(j).getRed();
						int g = lay.pixels.get(i).get(j).getGreen();
						int b = lay.pixels.get(i).get(j).getBlue();
						int a = lay.pixels.get(i).get(j).getAlpha();

						lay.pixels.get(i).set(j,
								new Pixel((int) (r / value), (int) (g / value), (int) (b / value), (int) a));

					}
				}
			}

		} else {
			for (Layer lay : image.layers) {
				for (Selection sale : image.selections) {
					if (!sale.active)
						continue;
					for (int i = sale.y; i < sale.height + sale.y; i++) {
						for (int j = sale.x; j < sale.width + sale.x; j++) {

							int r = lay.pixels.get(i).get(j).getRed();
							int g = lay.pixels.get(i).get(j).getGreen();
							int b = lay.pixels.get(i).get(j).getBlue();
							int a = lay.pixels.get(i).get(j).getAlpha();

							lay.pixels.get(i).set(j,
									new Pixel((int) (r / value), (int) (g / value), (int) (b / value), (int) a));

						}
					}
				}
			}
		}
		image.revalidate();
		image.repaint();
	}
}

//--------------------------------------------------------------------------------------
class DivInvert extends Operation {

	public DivInvert(double val) {
		super("DivInvert");
//		changePixel(new Pixel(r, g, b, a));
		value = val;
	}

	@Override
	public void work(Image image) {
		if (image.selections.size() == 0) {

			for (Layer lay : image.layers) {
				for (int i = 0; i < lay.height; i++) {
					for (int j = 0; j < lay.width; j++) {
						int r = lay.pixels.get(i).get(j).getRed();
						int g = lay.pixels.get(i).get(j).getGreen();
						int b = lay.pixels.get(i).get(j).getBlue();
						int a = lay.pixels.get(i).get(j).getAlpha();

						lay.pixels.get(i).set(j,
								new Pixel((int) (value / r), (int) (value / g), (int) (value / b), (int) a));

					}
				}
			}

		} else {
			for (Layer lay : image.layers) {
				for (Selection sale : image.selections) {
					if (!sale.active)
						continue;
					for (int i = sale.y; i < sale.height + sale.y; i++) {
						for (int j = sale.x; j < sale.width + sale.x; j++) {

							int r = lay.pixels.get(i).get(j).getRed();
							int g = lay.pixels.get(i).get(j).getGreen();
							int b = lay.pixels.get(i).get(j).getBlue();
							int a = lay.pixels.get(i).get(j).getAlpha();

							lay.pixels.get(i).set(j,
									new Pixel((int) (value / r), (int) (value / g), (int) (value / b), (int) a));

						}
					}
				}
			}
		}
		image.revalidate();
		image.repaint();
	}
}

//--------------------------------------------------------------------------------------
class Pow extends Operation {

	public Pow(double val) {
		super("Pow");
//		changePixel(new Pixel(r, g, b, a));
		value = val;
	}

	@Override
	public void work(Image image) {
		if (image.selections.size() == 0) {

			for (Layer lay : image.layers) {
				for (int i = 0; i < lay.height; i++) {
					for (int j = 0; j < lay.width; j++) {
						int r = lay.pixels.get(i).get(j).getRed();
						int g = lay.pixels.get(i).get(j).getGreen();
						int b = lay.pixels.get(i).get(j).getBlue();
						int a = lay.pixels.get(i).get(j).getAlpha();

						lay.pixels.get(i).set(j, new Pixel((int) Math.pow(r, value), (int) Math.pow(g, value),
								(int) Math.pow(b, value), (int) a));

					}
				}
			}

		} else {
			for (Layer lay : image.layers) {
				for (Selection sale : image.selections) {
					if (!sale.active)
						continue;
					for (int i = sale.y; i < sale.height + sale.y; i++) {
						for (int j = sale.x; j < sale.width + sale.x; j++) {

							int r = lay.pixels.get(i).get(j).getRed();
							int g = lay.pixels.get(i).get(j).getGreen();
							int b = lay.pixels.get(i).get(j).getBlue();
							int a = lay.pixels.get(i).get(j).getAlpha();

							lay.pixels.get(i).set(j, new Pixel((int) Math.pow(r, value), (int) Math.pow(g, value),
									(int) Math.pow(b, value), (int) a));

						}
					}
				}
			}
		}
		image.revalidate();
		image.repaint();
	}
}

//--------------------------------------------------------------------------------------
class Log extends Operation {

	public Log(double val) {
		super("Log");
//		changePixel(new Pixel(r, g, b, a));
		value = val;
	}

	@Override
	public void work(Image image) {
		if (image.selections.size() == 0) {

			for (Layer lay : image.layers) {
				for (int i = 0; i < lay.height; i++) {
					for (int j = 0; j < lay.width; j++) {
						int r = lay.pixels.get(i).get(j).getRed();
						int g = lay.pixels.get(i).get(j).getGreen();
						int b = lay.pixels.get(i).get(j).getBlue();
						int a = lay.pixels.get(i).get(j).getAlpha();

						lay.pixels.get(i).set(j,
								new Pixel((int) (Math.log(r) / (Math.log(value))),
										(int) (Math.log(g) / (Math.log(value))),
										(int) (Math.log(b) / (Math.log(value))), (int) a));

					}
				}
			}

		} else {
			for (Layer lay : image.layers) {
				for (Selection sale : image.selections) {
					if (!sale.active)
						continue;
					for (int i = sale.y; i < sale.height + sale.y; i++) {
						for (int j = sale.x; j < sale.width + sale.x; j++) {

							int r = lay.pixels.get(i).get(j).getRed();
							int g = lay.pixels.get(i).get(j).getGreen();
							int b = lay.pixels.get(i).get(j).getBlue();
							int a = lay.pixels.get(i).get(j).getAlpha();

							lay.pixels.get(i).set(j,
									new Pixel((int) (Math.log(r) / (Math.log(value))),
											(int) (Math.log(g) / (Math.log(value))),
											(int) (Math.log(b) / (Math.log(value))), (int) a));

						}
					}
				}
			}
		}
		image.revalidate();
		image.repaint();
	}
}

//--------------------------------------------------------------------------------------
class Abs extends Operation {

	public Abs() {
		super("Abs");
	}

	@Override
	public void work(Image image) {
		if (image.selections.size() == 0) {

			for (Layer lay : image.layers) {
				for (int i = 0; i < lay.height; i++) {
					for (int j = 0; j < lay.width; j++) {
						int r = lay.pixels.get(i).get(j).getRed();
						int g = lay.pixels.get(i).get(j).getGreen();
						int b = lay.pixels.get(i).get(j).getBlue();
						int a = lay.pixels.get(i).get(j).getAlpha();

						lay.pixels.get(i).set(j,
								new Pixel((int) Math.abs(r), (int) Math.abs(g), (int) Math.abs(b), (int) a));

					}
				}
			}

		} else {
			for (Layer lay : image.layers) {
				for (Selection sale : image.selections) {
					if (!sale.active)
						continue;
					for (int i = sale.y; i < sale.height + sale.y; i++) {
						for (int j = sale.x; j < sale.width + sale.x; j++) {

							int r = lay.pixels.get(i).get(j).getRed();
							int g = lay.pixels.get(i).get(j).getGreen();
							int b = lay.pixels.get(i).get(j).getBlue();
							int a = lay.pixels.get(i).get(j).getAlpha();

							lay.pixels.get(i).set(j,
									new Pixel((int) Math.abs(r), (int) Math.abs(g), (int) Math.abs(b), (int) a));

						}
					}
				}
			}
		}
		image.revalidate();
		image.repaint();
	}
}

//--------------------------------------------------------------------------------------
class Min extends Operation {

	public Min(double val) {
		super("Min");
//		changePixel(new Pixel(r, g, b, a));
		value = val;
	}

	@Override
	public void work(Image image) {
		if (image.selections.size() == 0) {

			for (Layer lay : image.layers) {
				for (int i = 0; i < lay.height; i++) {
					for (int j = 0; j < lay.width; j++) {
						int r = lay.pixels.get(i).get(j).getRed();
						int g = lay.pixels.get(i).get(j).getGreen();
						int b = lay.pixels.get(i).get(j).getBlue();
						int a = lay.pixels.get(i).get(j).getAlpha();

						lay.pixels.get(i).set(j, new Pixel((int) (value < r ? value : r), (int) (value < g ? value : g),
								(int) (value < b ? value : b), (int) a));

					}
				}
			}

		} else {
			for (Layer lay : image.layers) {
				for (Selection sale : image.selections) {
					if (!sale.active)
						continue;
					for (int i = sale.y; i < sale.height + sale.y; i++) {
						for (int j = sale.x; j < sale.width + sale.x; j++) {

							int r = lay.pixels.get(i).get(j).getRed();
							int g = lay.pixels.get(i).get(j).getGreen();
							int b = lay.pixels.get(i).get(j).getBlue();
							int a = lay.pixels.get(i).get(j).getAlpha();

							lay.pixels.get(i).set(j, new Pixel((int) (value < r ? value : r),
									(int) (value < g ? value : g), (int) (value < b ? value : b), (int) a));

						}
					}
				}
			}
		}
		image.revalidate();
		image.repaint();
	}
}

//--------------------------------------------------------------------------------------
class Max extends Operation {

	public Max(double val) {
		super("Max");
//		changePixel(new Pixel(r, g, b, a));
		value = val;
	}

	@Override
	public void work(Image image) {
		if (image.selections.size() == 0) {

			for (Layer lay : image.layers) {
				for (int i = 0; i < lay.height; i++) {
					for (int j = 0; j < lay.width; j++) {
						int r = lay.pixels.get(i).get(j).getRed();
						int g = lay.pixels.get(i).get(j).getGreen();
						int b = lay.pixels.get(i).get(j).getBlue();
						int a = lay.pixels.get(i).get(j).getAlpha();

						lay.pixels.get(i).set(j, new Pixel((int) (value > r ? value : r), (int) (value > g ? value : g),
								(int) (value > b ? value : b), (int) a));

					}
				}
			}

		} else {
			for (Layer lay : image.layers) {
				for (Selection sale : image.selections) {
					if (!sale.active)
						continue;
					for (int i = sale.y; i < sale.height + sale.y; i++) {
						for (int j = sale.x; j < sale.width + sale.x; j++) {

							int r = lay.pixels.get(i).get(j).getRed();
							int g = lay.pixels.get(i).get(j).getGreen();
							int b = lay.pixels.get(i).get(j).getBlue();
							int a = lay.pixels.get(i).get(j).getAlpha();

							lay.pixels.get(i).set(j, new Pixel((int) (value > r ? value : r),
									(int) (value > g ? value : g), (int) (value > b ? value : b), (int) a));

						}
					}
				}
			}
		}
		image.revalidate();
		image.repaint();
	}
}

//--------------------------------------------------------------------------------------
class Invert extends Operation {

	public Invert() {
		super("Invert");
//		changePixel(new Pixel(r, g, b, a));
		value = 255;
	}

	@Override
	public void work(Image image) {
		if (image.selections.size() == 0) {

			for (Layer lay : image.layers) {
				for (int i = 0; i < lay.height; i++) {
					for (int j = 0; j < lay.width; j++) {
						int r = lay.pixels.get(i).get(j).getRed();
						int g = lay.pixels.get(i).get(j).getGreen();
						int b = lay.pixels.get(i).get(j).getBlue();
						int a = lay.pixels.get(i).get(j).getAlpha();

						lay.pixels.get(i).set(j,
								new Pixel((int) (value - r), (int) (value - g), (int) (value - b), (int) a));

					}
				}
			}

		} else {
			for (Layer lay : image.layers) {
				for (Selection sale : image.selections) {
					if (!sale.active)
						continue;
					for (int i = sale.y; i < sale.height + sale.y; i++) {
						for (int j = sale.x; j < sale.width + sale.x; j++) {

							int r = lay.pixels.get(i).get(j).getRed();
							int g = lay.pixels.get(i).get(j).getGreen();
							int b = lay.pixels.get(i).get(j).getBlue();
							int a = lay.pixels.get(i).get(j).getAlpha();

							lay.pixels.get(i).set(j,
									new Pixel((int) (value - r), (int) (value - g), (int) (value - b), (int) a));

						}
					}
				}
			}
		}
		image.revalidate();
		image.repaint();
	}
}

//--------------------------------------------------------------------------------------
class Gray extends Operation {

	public Gray() {
		super("Gray");
	}

	@Override
	public void work(Image image) {
		if (image.selections.size() == 0) {

			for (Layer lay : image.layers) {
				for (int i = 0; i < lay.height; i++) {
					for (int j = 0; j < lay.width; j++) {
						int r = lay.pixels.get(i).get(j).getRed();
						int g = lay.pixels.get(i).get(j).getGreen();
						int b = lay.pixels.get(i).get(j).getBlue();
						int a = lay.pixels.get(i).get(j).getAlpha();

						double gray = (r + g + b) / 3;

						lay.pixels.get(i).set(j, new Pixel((int) gray, (int) gray, (int) gray, (int) a));

					}
				}
			}

		} else {
			for (Layer lay : image.layers) {
				for (Selection sale : image.selections) {
					if (!sale.active)
						continue;
					for (int i = sale.y; i < sale.height + sale.y; i++) {
						for (int j = sale.x; j < sale.width + sale.x; j++) {

							int r = lay.pixels.get(i).get(j).getRed();
							int g = lay.pixels.get(i).get(j).getGreen();
							int b = lay.pixels.get(i).get(j).getBlue();
							int a = lay.pixels.get(i).get(j).getAlpha();

							double gray = (r + g + b) / 3;

							lay.pixels.get(i).set(j, new Pixel((int) gray, (int) gray, (int) gray, (int) a));

						}
					}
				}
			}
		}
		image.revalidate();
		image.repaint();
	}
}

//--------------------------------------------------------------------------------------
class BlackWhite extends Operation {

	public BlackWhite() {
		super("BlackWhite");
	}

	@Override
	public void work(Image image) {
		if (image.selections.size() == 0) {

			for (Layer lay : image.layers) {
				for (int i = 0; i < lay.height; i++) {
					for (int j = 0; j < lay.width; j++) {
						int r = lay.pixels.get(i).get(j).getRed();
						int g = lay.pixels.get(i).get(j).getGreen();
						int b = lay.pixels.get(i).get(j).getBlue();
						int a = lay.pixels.get(i).get(j).getAlpha();

						double gray = (r + g + b) / 3;

						if (gray > 127) {
							lay.pixels.get(i).set(j, new Pixel(255, 255, 255, a));
						} else {
							lay.pixels.get(i).set(j, new Pixel(0, 0, 0, a));
						}
					}
				}
			}

		} else {
			for (Layer lay : image.layers) {
				for (Selection sale : image.selections) {
					if (!sale.active)
						continue;
					for (int i = sale.y; i < sale.height + sale.y; i++) {
						for (int j = sale.x; j < sale.width + sale.x; j++) {

							int r = lay.pixels.get(i).get(j).getRed();
							int g = lay.pixels.get(i).get(j).getGreen();
							int b = lay.pixels.get(i).get(j).getBlue();
							int a = lay.pixels.get(i).get(j).getAlpha();

							double gray = (r + g + b) / 3;

							if (gray > 127) {
								lay.pixels.get(i).set(j, new Pixel(255, 255, 255, a));
							} else {
								lay.pixels.get(i).set(j, new Pixel(0, 0, 0, a));
							}

						}
					}
				}
			}
		}
		image.revalidate();
		image.repaint();
	}
}

//--------------------------------------------------------------------------------------
class Blur extends Operation {

	public Blur() {
		super("Blur");
	}

	@Override
	public void work(Image image) {
		Image new_Image = new Image(image.width, image.height);
		if (image.selections.size() == 0) {

			for (int lay = 0; lay < image.layers.size(); lay++) {
				Layer tempLay = new Layer(image.width, image.height);

				for (int i = 0; i < image.layers.get(lay).height; i++) {
					for (int j = 0; j < image.layers.get(lay).width; j++) {
						int count = 0;
						int r = 0;
						int g = 0;
						int b = 0;

						for (int ii = i - 1; ii < i + 1; ii++) {
							if (ii >= 0 && ii < image.height) {
								for (int jj = j - 1; jj < j + 1; jj++) {
									if (jj >= 0 && jj < image.width) {
										count++;
										r += image.layers.get(lay).pixels.get(ii).get(jj).getRed();
										g += image.layers.get(lay).pixels.get(ii).get(jj).getGreen();
										b += image.layers.get(lay).pixels.get(ii).get(jj).getBlue();

										tempLay.pixels.get(i).set(j, new Pixel(r / count, g / count, b / count,
												image.layers.get(lay).pixels.get(i).get(j).getAlpha()));

										// --
									}
								}
							}
						}

					}
				}
				image.layers.set(lay, tempLay);
			}

		} else {
			for (int lay = 0; lay < image.layers.size(); lay++) {

				Layer tempLay = new Layer(image.width, image.height);
				for (int i = 0; i < image.layers.get(lay).height; i++) {
					for (int j = 0; j < image.layers.get(lay).width; j++) {
						tempLay.pixels.get(i).set(j, image.layers.get(lay).pixels.get(i).get(j));
					}
				}

				for (Selection sale : image.selections) {
					if (!sale.active)
						continue;
					for (int i = sale.y; i < sale.height + sale.y; i++) {
						for (int j = sale.x; j < sale.width + sale.x; j++) {

							int count = 0;
							int r = 0;
							int g = 0;
							int b = 0;

							for (int ii = i - 1; ii < i + 1; ii++) {
								if (ii >= 0 && ii < image.height) {
									for (int jj = j - 1; jj < j + 1; jj++) {
										if (jj >= 0 && jj < image.width) {
											count++;
											r += image.layers.get(lay).pixels.get(ii).get(jj).getRed();
											g += image.layers.get(lay).pixels.get(ii).get(jj).getGreen();
											b += image.layers.get(lay).pixels.get(ii).get(jj).getBlue();

											tempLay.pixels.get(i).set(j, new Pixel(r / count, g / count, b / count,
													image.layers.get(lay).pixels.get(i).get(j).getAlpha()));

											
										}
									}
								}
							}

						}
					}
				}
				image.layers.set(lay, tempLay);
			}
		}

		image.revalidate();
		image.repaint();
	}
}