; ModuleID = 'k.c'
source_filename = "k.c"
target datalayout = "e-m:e-p270:32:32-p271:32:32-p272:64:64-i64:64-i128:128-f80:128-n8:16:32:64-S128"
target triple = "x86_64-pc-linux-gnu"

@x = external local_unnamed_addr global i32, align 4
@_ZL1y = internal unnamed_addr global i32 0, align 4
@llvm.global_ctors = appending global [1 x { i32, ptr, ptr }] [{ i32, ptr, ptr } { i32 65535, ptr @_GLOBAL__sub_I_k.c, ptr null }]

; Function Attrs: mustprogress noinline norecurse optnone sspstrong uwtable
define dso_local noundef i32 @main() local_unnamed_addr #0 {
  %1 = alloca i32, align 4
  store i32 0, ptr %1, align 4
  store i32 1, ptr @x, align 4, !tbaa !5
  %2 = load i32, ptr @_ZL1y, align 4, !tbaa !5
  %3 = add nsw i32 %2, 3
  store i32 %3, ptr @_ZL1y, align 4, !tbaa !5
  call void @_Z3foov()
  %4 = add nsw i32 6, 6
  %5 = add nsw i32 %4, 6
  %6 = load i32, ptr @_ZL1y, align 4, !tbaa !5
  %7 = sub nsw i32 %6, %5
  store i32 %7, ptr @_ZL1y, align 4, !tbaa !5
  %8 = load i32, ptr @x, align 4, !tbaa !5
  ret i32 %8
}

declare void @_Z3foov() local_unnamed_addr #1

; Function Attrs: mustprogress nofree norecurse nosync nounwind sspstrong willreturn memory(readwrite, argmem: none, inaccessiblemem: none) uwtable
define internal void @_GLOBAL__sub_I_k.c() #2 section ".text.startup" {
  %1 = load i32, ptr @x, align 4
  %2 = icmp sgt i32 %1, 1
  %3 = select i1 %2, i32 0, i32 %1
  store i32 %3, ptr @_ZL1y, align 4, !tbaa !5
  ret void
}

attributes #0 = { mustprogress noinline norecurse optnone sspstrong uwtable "min-legal-vector-width"="0" "no-trapping-math"="true" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cmov,+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "tune-cpu"="generic" }
attributes #1 = { "no-trapping-math"="true" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cmov,+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "tune-cpu"="generic" }
attributes #2 = { mustprogress nofree norecurse nosync nounwind sspstrong willreturn memory(readwrite, argmem: none, inaccessiblemem: none) uwtable "min-legal-vector-width"="0" "no-trapping-math"="true" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cmov,+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "tune-cpu"="generic" }

!llvm.module.flags = !{!0, !1, !2, !3}
!llvm.ident = !{!4}

!0 = !{i32 1, !"wchar_size", i32 4}
!1 = !{i32 8, !"PIC Level", i32 2}
!2 = !{i32 7, !"PIE Level", i32 2}
!3 = !{i32 7, !"uwtable", i32 2}
!4 = !{!"clang version 18.1.8"}
!5 = !{!6, !6, i64 0}
!6 = !{!"int", !7, i64 0}
!7 = !{!"omnipotent char", !8, i64 0}
!8 = !{!"Simple C++ TBAA"}
