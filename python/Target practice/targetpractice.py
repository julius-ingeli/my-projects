import pygame
import sys
import random
from time import sleep

#crosshair

class Crosshair(pygame.sprite.Sprite):
    def __init__(self,picture_path):
        super().__init__()
        self.image = pygame.image.load((picture_path))
        self.rect = self.image.get_rect()
        self.gunshot = pygame.mixer.Sound('pistol.wav')
    def update(self):
        self.rect.center=pygame.mouse.get_pos()
        
    def shoot(self):
        self.gunshot.play()
        pygame.sprite.spritecollide(crshr,target_group,True)
        
#drawing targets 

class Target(pygame.sprite.Sprite):
    def __init__(self, picutre_path,pos_x,pos_y):
        super().__init__()
        self.image = pygame.image.load(picutre_path)
        self.rect = self.image.get_rect()
        self.rect.center = [pos_x,pos_y]

#init pygame
pygame.init()
clock = pygame.time.Clock()
pygame.mouse.set_visible(False)
    

#creating screen
screenH = 800
screenW = 600

screen = pygame.display.set_mode((screenH,screenW))


#title and icon

pygame.display.set_caption("Target practice")
icon = pygame.image.load('crosshair.png')
pygame.display.set_icon(icon)

#loading sprites
background = pygame.image.load("BG.png")
target = pygame.image.load(('crosshair.png'))
crosshair = pygame.image.load(('crosshairSmall.png'))
bullethole = pygame.image.load('bullethole.png')

#crosshair
crshr = Crosshair('crosshairSmall.png')
crosshair_group = pygame.sprite.Group()
crosshair_group.add(crshr)

#target

target_group = pygame.sprite.Group()
for target in range(6):
    new_target = Target("target.png",random.randrange(0,screenW),random.randrange(0,screenH))
    target_group.add(new_target)


#gameloop

running = True
while running:
    
    ev = pygame.event.get()
    for event in ev:
        if event.type == pygame.QUIT:
            pygame.quit()
            sys.exit()
        if event.type == pygame.MOUSEBUTTONDOWN:
            crshr.shoot()
            
        

    pygame.display.flip()
    screen.blit(background,(0,0))
    target_group.draw(screen)
    crosshair_group.draw(screen)
    crosshair_group.update()
    clock.tick(60)
