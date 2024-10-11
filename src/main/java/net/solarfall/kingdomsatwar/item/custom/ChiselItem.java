package net.solarfall.kingdomsatwar.item.custom;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.solarfall.kingdomsatwar.block.ModBlocks;

import java.util.Map;

public class ChiselItem extends Item {
    private static final Map<Block, Block> CHISLE_MAP = 
        Map.of(
            Blocks.STONE, Blocks.STONE_BRICKS,
            Blocks.GRANITE, Blocks.POLISHED_GRANITE,
            Blocks.DIORITE, Blocks.POLISHED_DIORITE,
            Blocks.ANDESITE, Blocks.POLISHED_ANDESITE,
            Blocks.NETHERRACK, ModBlocks.VITARIUM_ORE.get() // Calling custom block need to do .get()
        );
    
    public ChiselItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Block clickedBlock = level.getBlockState(context.getClickedPos()).getBlock();

        if (CHISLE_MAP.containsKey(clickedBlock)) {
            // Remember this is server side
            if (!level.isClientSide()) {
                Block toPlace = CHISLE_MAP.get(clickedBlock);
                level.setBlockAndUpdate(context.getClickedPos(), toPlace.defaultBlockState());
                
                context.getItemInHand().hurtAndBreak(1, ((ServerLevel) level), context.getPlayer(),
                    item -> context.getPlayer().onEquippedItemBroken(item, EquipmentSlot.MAINHAND));

                level.playSound(null, context.getClickedPos(), SoundEvents.GRINDSTONE_USE, SoundSource.BLOCKS);
            }
        }

        return InteractionResult.SUCCESS;
    }
    
}
