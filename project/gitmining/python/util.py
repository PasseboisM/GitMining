def count_ele(list,range_lo,range_hi):
    count = 0
    for i in list:
        if (range_lo <= i) & (i < range_hi):
            count += 1
    return count